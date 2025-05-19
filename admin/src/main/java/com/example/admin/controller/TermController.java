package com.example.admin.controller;

import com.example.admin.service.TermService;
import com.example.admin.service.dto.ReqTermDTO;
import com.example.admin.service.dto.UpdateReqTermDTO;
import com.example.base.dto.CommonResponse;
import com.example.base.dto.SearchTermDTO;
import com.example.base.dto.SearchTermDTO.SortBy;
import com.example.base.enums.SortType;
import com.example.base.enums.TermType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/terms")
public class TermController {

  private final TermService termService;

  @PostMapping
  public void createTerm(@RequestBody ReqTermDTO req) {
    termService.createTerm(req);
  }

  @GetMapping("/list")
  public CommonResponse<?> getTermsList(
      @RequestParam(required = false) TermType id,
      @RequestParam(required = false) SortBy sortBy,
      @RequestParam(required = false) SortType sortType,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size
  ) {
    SearchTermDTO req = new SearchTermDTO(id, sortBy, sortType, page, size);
    return CommonResponse.success(termService.getTermsList(req));
  }

  @PutMapping("/{id}/{version}")
  public void updateTerm(
      @PathVariable TermType id,
      @PathVariable Long version,
      @RequestBody UpdateReqTermDTO req) {
    termService.updateTerm(id, version, req);
  }


}
