package com.example.base.dto;

import org.springframework.http.HttpStatus;

public record CommonResponse<T>(
    String code,
    HttpStatus httpStatus,
    String message,
    T data
) {

  public static<T> CommonResponse<T> success(T data) {
    return new CommonResponse<>("000001", HttpStatus.OK,"요청이 성공적으로 처리되었습니다.", data);
  }

}
