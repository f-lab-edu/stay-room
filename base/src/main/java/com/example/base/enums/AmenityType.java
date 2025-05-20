package com.example.base.enums;

public enum AmenityType {
  BBQ("바베큐"),
  POOL("수영장"),
  PARKING("주차장"),
  ELEVATOR("엘리베이터"),
  MICROWAVE("전자레인지"),
  KARAOKE("노래방"),
  PUBLIC_SPA("공용스파"),
  CAFE("카페"),
  SAUNA("사우나"),
  KITCHEN("주방/식당"),
  PLAYROOM("놀이방"),
  WASHER("세탁기"),
  DRYER("건조기"),
  HAIRDRYER("탈수기"),
  COOKING_ALLOWED("취사가능");

  private final String label;

  AmenityType(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}
