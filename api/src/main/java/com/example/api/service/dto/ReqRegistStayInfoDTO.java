package com.example.api.service.dto;

import com.example.base.dto.DtoValidationCheck;
import com.example.base.enums.LocationType;
import com.example.base.enums.StayType;

public record ReqRegistStayInfoDTO(
    String name,
    LocationType location,
    String description,
    Long sellerId,
    StayType stayType
) implements DtoValidationCheck {
  @Override
  public void check() {

  }
}
