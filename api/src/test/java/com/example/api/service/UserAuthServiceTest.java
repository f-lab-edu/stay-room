package com.example.api.service;

import com.example.api.service.dto.ReqRegistUserDTO;
import com.example.base.entity.Users;
import com.example.base.enums.GenderType;
import com.example.base.enums.UserProviderType;
import com.example.base.repository.UsersRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import org.apache.catalina.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Transactional // 테스트 후 롤백
public class UserAuthServiceTest {
  @Autowired
  private UserAuthService userAuthService;
  @Autowired
  private UsersRepository usersRepository;

  private final String testEmail = "test@test.com";
  private final String testPassword = "test12345";
  private final LocalDate testBirth = LocalDate.of(1995,12,24);
  private final String displayName = "test";

  @BeforeEach
  void setUp() {
    Users user = Users.builder()
            .email(testEmail)
            .password(testPassword)
            .birthday(testBirth)
            .gender(GenderType.FEMALE)
            .displayName(displayName)
            .provider(UserProviderType.NORMAL)
            .build();

    usersRepository.save(user);
  }

  @Test
  void 이메일_중복일때() {
    Assertions.assertThat(usersRepository.existsUsersByEmail(testEmail)).isEqualTo(true);
  }

}
