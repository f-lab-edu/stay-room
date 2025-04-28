package com.example.base.exception;

import com.example.base.enums.ErrorType;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

@Getter
public class CommonException extends RuntimeException {

  private ErrorType errorType;
  private Map<String, Object> parameters;

  public CommonException(ErrorType errorType) {
    this(errorType, new HashMap<>());
  }

  public CommonException(ErrorType errorType, Map<String, Object> parameters) {
    this.errorType = errorType;
    this.parameters = parameters;
  }

}
