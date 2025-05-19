package com.example.api.service.dto;

import com.example.base.dto.DtoValidationCheck;
import com.example.base.enums.ErrorType;
import com.example.base.exception.CommonException;
import com.example.base.utils.ValidationUtil;

public record ReqSendEmailDTO(String email) implements DtoValidationCheck {
  @Override
  public void check() {
    if (email == null || email.isBlank()) {
      throw new CommonException(ErrorType.REQUIRED_FIELD_EMPTY);
    }
    ValidationUtil.isValidEmail(email);
  }
}
