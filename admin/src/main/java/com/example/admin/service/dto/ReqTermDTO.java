package com.example.admin.service.dto;

import com.example.base.enums.TermType;
import java.time.LocalDate;

public record ReqTermDTO(TermType id, LocalDate effectiveDate, String contents, boolean required) {

}
