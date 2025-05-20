package com.example.base.enums;

import com.example.base.exception.CommonException;
import java.util.Arrays;

public enum LocationType {
  GYEONGGI("경기"),
  JEJU("제주도"),
  SEOUL("서울"),
  INCHEON("인천"),
  DAEGU("대구"),
  DAEJEON("대전"),
  CHUNGNAM("충남"),
  GYEONGNAM("경남"),
  BUSAN("부산"),
  JEONBUK("전북"),
  ULSAN("울산"),
  GWANGJU("광주"),
  GANGWON("강원"),
  GYEONGBUK("경북"),
  JEONNAM("전남"),
  CHUNGBUK("충북"),
  SEJONG("세종");

  private final String label;

  LocationType(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

  public static LocationType fromLabel(String label) {
    // values()는 열거형의 모든 상수를 배열에 담아 반환한다
   return Arrays.stream(values())// 배열을 stream으로 변환해서 하나씩 순회하며 처리하겠다
        .filter(r -> r.label.equals(label)) // 해당 객체의 label 필드와 입력값 label이 일치하는지 확인
        .findFirst() // 조건에 처음으로 일치하는 enum을 optional로 반환
        .orElseThrow(() -> new CommonException(ErrorType.BAD_REQUSET_VALUE)); // 못찾았을 경우 예외처리
  }
}
