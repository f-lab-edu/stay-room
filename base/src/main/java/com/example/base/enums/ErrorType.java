package com.example.base.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorType {
  BAD_REQUEST("100001", HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
  ADMIN_UPDATE_CONFLICT("100002", HttpStatus.CONFLICT, "다른 관리자가 먼저 수정했습니다. 다시 시도해주세요."),
  REQUIRED_FIELD_EMPTY("100003", HttpStatus.BAD_REQUEST, "필수 입력값이 입력되지 않았습니다."),
  NOT_VAILD_EMAIL_FORMAT("100004", HttpStatus.BAD_REQUEST, "유효한 이메일 형식이 아닙니다."),
  CODE_REQUEST_NOT_POSIBLE("100005", HttpStatus.BAD_REQUEST, "요청한지 1분 이내에는 요청할 수 없습니다."),
  VERIFY_TIME_OUT("100006", HttpStatus.BAD_REQUEST, "인증 입력 시간이 초과되었습니다. 인증 코드를 재요청해주세요."),
  INVALID_VERIFY_CODE("100007", HttpStatus.BAD_REQUEST, "인증 번호가 일치하지 않습니다. 다시 시도해주세요."),
  PASSWORD_NOT_EQUAL("100008", HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
  INVALID_PASSWORD("100009", HttpStatus.BAD_REQUEST, "유효하지 않은 비밀번호입니다."),
  EMAIL_CONFLICT("100010", HttpStatus.CONFLICT, "이미 사용 중인 이메일입니다."),
  NOT_VERIFY_EMAIL("100011",HttpStatus.BAD_REQUEST,"메일 인증이 유효하지 않습니다. 메일을 재인증해주세요."),
  REQUIRED_TERMS_NOT_AGREED("100012",HttpStatus.BAD_REQUEST,"필수 약관에 동의하지 않았습니다."),
  BAD_REQUSET_VALUE("100013", HttpStatus.BAD_REQUEST,"해당 값이 존재하지 않습니다."),
  DUPLICATE_VALUE("100014", HttpStatus.BAD_REQUEST,"중복된 값이 있습니다.");

  ErrorType(String code, HttpStatus httpStatus, String message) {
    this.code = code;
    this.httpStatus = httpStatus;
    this.message = message;
  }


  private final String code;
  private final HttpStatus httpStatus;
  private final String message;
}
