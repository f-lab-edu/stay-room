package com.example.api.controller;

import com.example.api.service.UserTermService;
import com.example.base.dto.CommonResponse;
import com.example.base.enums.TermType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/terms")
public class UserTermController {

  private final UserTermService userTermService;

  @GetMapping("/list")
  public CommonResponse<?> getActiveTermList() {
    return CommonResponse.success(userTermService.getActiveTermList());
  }

  @GetMapping("/active/{id}")
  public CommonResponse<?> getActiveTerm(@PathVariable TermType id) {
    return CommonResponse.success(userTermService.getActiveTermInfo(id));

  }

}
