package com.example.api.controller;

import com.example.api.service.StayService;
import com.example.api.service.dto.ReqRegistRoomDetailDTO;
import com.example.api.service.dto.ReqRegistStayInfoDTO;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stay")
public class StayController {

  private final StayService stayService;

  @Operation(summary = "숙소 등록", description = "숙소를 등록합니다.")
  @PostMapping
  public void registStayInfo(@RequestBody ReqRegistStayInfoDTO req) {
    req.check();
    stayService.registStayInfo(req);
  }

  @Operation(summary = "숙소 방타입 정보 등록", description = "숙소 내의 방타입에 대한 세부 정보를 등록합니다.")
  @PostMapping("/detail/{stayInfoId}")
  public void registStayRoomDetail(
      @PathVariable Long stayInfoId,
      @RequestBody List<ReqRegistRoomDetailDTO> req) {
    stayService.registStayRoomDetail(stayInfoId, req);
  }

}
