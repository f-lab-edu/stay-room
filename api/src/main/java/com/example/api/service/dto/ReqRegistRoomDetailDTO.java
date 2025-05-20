package com.example.api.service.dto;

import com.example.base.dto.DtoValidationCheck;
import java.util.List;

public record ReqRegistRoomDetailDTO(
    String roomType,
    int count,
    int price,
    List<ReqRoomNumberDTO> roomNumberList
) implements DtoValidationCheck {
  @Override
  public void check() {

  }
}
