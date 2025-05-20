package com.example.api.service.dto;

import com.example.base.dto.DtoValidationCheck;

public record ReqRoomNumberDTO(int roomNumber) implements DtoValidationCheck {

  @Override
  public void check() {

  }
}
