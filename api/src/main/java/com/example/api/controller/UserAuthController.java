package com.example.api.controller;

import com.example.api.service.UserAuthService;
import com.example.api.service.dto.ReqCodeVerifyDTO;
import com.example.api.service.dto.ReqRegistUserDTO;
import com.example.api.service.dto.ReqSendEmailDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/auth/")
public class UserAuthController {

  private final UserAuthService userAuthService;

  @Operation(summary = "이메일 인증 코드 전송", description = "이메일 인증을 위한 코드를 전송합니다.")
  @PostMapping("/email/code")
  public void sendVerifyCodeEmail(@RequestBody ReqSendEmailDTO req) {
    req.check();
    userAuthService.sendEmailVerifyCode(req);
  }

  @Operation(summary = "이메일 인증 코드 검증", description = "인증 코드를 검증합니다.")
  @PostMapping("/email/verify")
  public void verifyCode(@RequestBody ReqCodeVerifyDTO req) {
    req.check();
    userAuthService.verifyCode(req);
  }

  @Operation(summary = "일반 회원가입", description = "이메일로 회원가입합니다.")
  @PostMapping("/signup")
  public void registUser(@RequestBody ReqRegistUserDTO req) {
    req.check();
    userAuthService.registUser(req);
  }
}
