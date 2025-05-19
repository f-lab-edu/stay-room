package com.example.api.service;

import com.example.api.service.dto.ReqCodeVerifyDTO;
import com.example.api.service.dto.ReqRegistUserDTO;
import com.example.api.service.dto.ReqSendEmailDTO;
import com.example.api.service.dto.ReqUserTermDTO;
import com.example.base.entity.Terms;
import com.example.base.entity.UserTerm;
import com.example.base.entity.UserTermId;
import com.example.base.entity.Users;
import com.example.base.enums.ErrorType;
import com.example.base.enums.GenderType;
import com.example.base.enums.RoleType;
import com.example.base.enums.TermType;
import com.example.base.enums.UserProviderType;
import com.example.base.exception.CommonException;
import com.example.base.repository.TermsRepository;
import com.example.base.repository.UserTermRepository;
import com.example.base.repository.UsersRepository;
import jakarta.transaction.Transactional;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserAuthService {

  private final RedisTemplate<String, Object> redisTemplate;
  private final EmailService emailService;
  private final UsersRepository usersRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserTermRepository userTermRepository;
  private final TermsRepository termsRepository;

  private static final String EMAIL_VERIFY_CODE = "email:verify:";
  private static final String EMAIL_VERIFY_LIMIT = "email:verify:limit:";
  private static final String EMAIL_AUTH_SAVE = "email:auth:save:";

  public void sendEmailVerifyCode(ReqSendEmailDTO req) {
    // 인증 코드 요청 제한이 걸려있는지 확인
    if (Boolean.TRUE.equals(redisTemplate.hasKey(EMAIL_VERIFY_LIMIT + req.email()))) {
      throw new CommonException(ErrorType.CODE_REQUEST_NOT_POSIBLE);
    }
    // 코드 생성
    String code = createCode();
    // 이메일 보내기
    emailService.sendVerifyCodeEmail(req.email(), code);
    // 코드 검증을 위해 redis에 코드를 저장
    redisTemplate.opsForValue().set(EMAIL_VERIFY_CODE + req.email(), code, Duration.ofMinutes(3));
    // 1분 이내 재요청 제한을 위한 limit 코드 redis에 저장
    redisTemplate.opsForValue().set(EMAIL_VERIFY_LIMIT + req.email(), "1", Duration.ofMinutes(1));
  }

  public void verifyCode(ReqCodeVerifyDTO req) {
    // redis에 코드가 없을 경우, 유효 시간 지난 것으로 간주
    String value = (String) redisTemplate.opsForValue().get(EMAIL_VERIFY_CODE + req.email());
    // 값이 null일 경우 유효 시간 지난 것으로 간주
    if (value == null) {
      throw new CommonException(ErrorType.VERIFY_TIME_OUT);
    }
    // 코드값이 맞는지 확인
    if (!value.equals(req.code())) {
      throw new CommonException(ErrorType.INVALID_VERIFY_CODE);
    }
    // 인증 완료시 키 삭제
    log.info("인증 성공 == {}", req.email());
    redisTemplate.delete(EMAIL_VERIFY_CODE + req.email());
    redisTemplate.delete(EMAIL_VERIFY_LIMIT + req.email());
    // 인증 완료된 이메일을 redis에 저장
    redisTemplate.opsForValue().set(EMAIL_AUTH_SAVE + req.email(), "1", Duration.ofMinutes(30));
  }

  @Transactional
  public void registUser(ReqRegistUserDTO req) {
    // 이미 등록된 이메일인지 확인
    if (usersRepository.existsUsersByEmail(req.email())) {
      throw new CommonException(ErrorType.EMAIL_CONFLICT);
    }
    // redis에 인증된 이메일이 존재하는지 확인
    if (Boolean.FALSE.equals(redisTemplate.hasKey(EMAIL_AUTH_SAVE + req.email()))) {
      throw new CommonException(ErrorType.NOT_VERIFY_EMAIL);
    }

    // 필수 약관 동의했는지 체크 진행
    List<Terms> requiredTerms = termsRepository.activeTermList(true);

    for (Terms required : requiredTerms) {
      TermType id = required.getTermId().id();
      Long version = required.getTermId().version();

      // 1. reqUserTermList에 필수 약관 정보가 전부 다 있는지 확인
      ReqUserTermDTO match = req.reqUserTermList().stream()
          .filter(dto -> dto.termTypeId().equals(id) && dto.version().equals(version))
          .findFirst()
          .orElseThrow(() -> new CommonException(ErrorType.REQUIRED_TERMS_NOT_AGREED));

      // 2. 필수 약관에 전부 다 동의했는지 확인
      if (!match.agreed()) {
        throw new CommonException(ErrorType.REQUIRED_TERMS_NOT_AGREED);
      }
    }
    // 유저 DB 등록
    Users user = Users.builder()
        .email(req.email())
        .password(passwordEncoder.encode(req.password()))
        .birthday(req.birthday())
        .gender(req.gender())
        .displayName(req.displayName())
        .provider(UserProviderType.NORMAL)
        .role(RoleType.USER)
        .deleteYn(false)
        .build();

    usersRepository.save(user);

    // 유저 약관 동의 정보 등록
    List<UserTerm> userTermList = req.reqUserTermList().stream()
        .map(dto -> new UserTerm(
            new UserTermId(user.getId(), dto.termTypeId(), dto.version()),
            dto.agreed()
        )).toList();

    userTermRepository.saveAll(userTermList);

   //  인증 이메일 redis에서 삭제
    redisTemplate.delete(EMAIL_AUTH_SAVE + req.email());
  }

  private String createCode() {
    Random random = new Random();
    return String.format("%06d", random.nextInt(1000000));
  }
}
