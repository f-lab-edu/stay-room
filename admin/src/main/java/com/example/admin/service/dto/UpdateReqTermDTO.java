package com.example.admin.service.dto;

import com.example.base.entity.Terms;
import com.example.base.enums.TermType;
import java.time.LocalDate;

public record UpdateReqTermDTO(String contents, boolean required,
                               LocalDate effectiveDate) {

}