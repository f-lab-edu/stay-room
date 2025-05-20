package com.example.base.enums;

public enum StayType {
  MOTEL("모텔"),
  HOTEL("호텔"),
  RESORT("리조트"),
  PENSION("펜션"),
  VILLA("빌라"),
  CAMPING("캠핑"),
  GUEST_HOUSE("게스트하우스"),
  HANOK("한옥");

  private final String label;

  StayType(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

}
