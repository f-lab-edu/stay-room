package com.example.base.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorType {
  BAD_REQUEST("100001", HttpStatus.BAD_REQUEST, "잘못된 요청입니다.");

  ErrorType(String code, HttpStatus httpStatus, String message) {
    this.code = code;
    this.httpStatus = httpStatus;
    this.message = message;
  }

  private final String code;
  private final HttpStatus httpStatus;
  private final String message;
}
