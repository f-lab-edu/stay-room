package com.example.base.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorType {
  BAD_REQUEST("100001", HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
  OPTIMISTIC_LOCK_CONFLICT("100002", HttpStatus.CONFLICT, "다른 관리자가 먼저 수정했습니다. 다시 시도해주세요.");
  ErrorType(String code, HttpStatus httpStatus, String message) {
    this.code = code;
    this.httpStatus = httpStatus;
    this.message = message;
  }

  private final String code;
  private final HttpStatus httpStatus;
  private final String message;
}
