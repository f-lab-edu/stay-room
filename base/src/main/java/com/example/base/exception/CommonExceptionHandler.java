package com.example.base.exception;

import com.example.base.enums.ErrorType;
import com.example.base.dto.CommonResponse;
import com.google.common.base.Joiner;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice // 예외를 JSON 형태로 반환하는 전역 처리 클래스
public class CommonExceptionHandler {

  @ExceptionHandler(value = CommonException.class)
  public ResponseEntity<CommonResponse<?>> exceptionHandler(CommonException e) {
    ErrorType errorType = e.getErrorType();
    Map<String, Object> parameters = e.getParameters();
    String param = Joiner.on(",").withKeyValueSeparator("=").join(parameters);
    log.error(errorType.getMessage() + param);

    return ResponseEntity.status(errorType.getHttpStatus())
            .body(new CommonResponse<>(errorType.getCode(), errorType.getHttpStatus(),errorType.getMessage(), null));
  }

  @ExceptionHandler({jakarta.persistence.OptimisticLockException.class,
      org.springframework.orm.ObjectOptimisticLockingFailureException.class})
  public ResponseEntity<CommonResponse<?>> optimisticLockHandler(Exception e) {
    ErrorType errorType = ErrorType.ADMIN_UPDATE_CONFLICT;

    return ResponseEntity.status(errorType.getHttpStatus()).body(new CommonResponse<>(errorType.getCode(),
        errorType.getHttpStatus(),errorType.getMessage(),null));
  }

}
