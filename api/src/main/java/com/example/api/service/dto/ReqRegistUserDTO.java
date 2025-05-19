package com.example.api.service.dto;

import com.example.base.dto.DtoValidationCheck;
import com.example.base.enums.ErrorType;
import com.example.base.enums.GenderType;
import com.example.base.exception.CommonException;
import com.example.base.utils.ValidationUtil;
import java.time.LocalDate;
import java.util.List;

public record ReqRegistUserDTO(String email,
                               String password,
                               String confirmPassword,
                               LocalDate birthday,
                               GenderType gender,
                               String displayName,
                               List<ReqUserTermDTO> reqUserTermList) implements DtoValidationCheck {

  @Override
  public void check() {
    if (email == null || email.isBlank()) {
      throw new CommonException(ErrorType.REQUIRED_FIELD_EMPTY);
    }
    if (password == null || password.isBlank()) {
      throw new CommonException(ErrorType.REQUIRED_FIELD_EMPTY);
    }
    if (confirmPassword == null || confirmPassword.isBlank()) {
      throw new CommonException(ErrorType.REQUIRED_FIELD_EMPTY);
    }
    if (birthday == null) {
      throw new CommonException(ErrorType.REQUIRED_FIELD_EMPTY);
    }
    if (gender == null) {
      throw new CommonException(ErrorType.REQUIRED_FIELD_EMPTY);
    }
    if (displayName == null || displayName.isBlank()) {
      throw new CommonException(ErrorType.REQUIRED_FIELD_EMPTY);
    }
    if (reqUserTermList == null) {
      throw new CommonException(ErrorType.REQUIRED_FIELD_EMPTY);
    }
    ValidationUtil.isValidEmail(email);
    ValidationUtil.passwordValidationCheck(password);
    ValidationUtil.passwordEqualCheck(password, confirmPassword);
  }
}
