package com.example.base.utils;

import com.example.base.enums.ErrorType;
import com.example.base.exception.CommonException;

public class ValidationUtil {
  public static void isValidEmail(String email) {
    boolean isVaild = email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
    if (!isVaild) {
      throw new CommonException(ErrorType.NOT_VAILD_EMAIL_FORMAT);
    }
  }

  // 비밀번호, 확인 비밀번호 일치 체크 메소드
  public static void passwordEqualCheck(String password, String confirmPassword) {
    if (!password.equals(confirmPassword)) {
      throw new CommonException(ErrorType.PASSWORD_NOT_EQUAL);
    }
  }
  // 비밀번호 유효성 검사 체크 메소드
  public static void passwordValidationCheck(String password) {
    // 하나 이상의 영문자, 숫자 포함 8자리 이상, 공백은 포함하지 않음
    String pattern = "^(?=.*[a-zA-Z])(?=.*[0-9])\\S{8,}$";
    if (!password.matches(pattern)) {
      throw new CommonException(ErrorType.INVALID_PASSWORD);
    }
  }
}
