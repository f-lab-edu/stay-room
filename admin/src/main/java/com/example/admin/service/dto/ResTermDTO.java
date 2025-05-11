package com.example.admin.service.dto;

import com.example.base.entity.Terms;
import com.example.base.enums.TermType;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ResTermDTO(TermType id, Long version, String contents, boolean required,
                         LocalDate effectiveDate, LocalDateTime createAt) {

  public static ResTermDTO from(Terms terms) {
    return new ResTermDTO(terms.getTermId().id(),terms.getTermId().version(),
        terms.getContents(),terms.isRequired(),terms.getEffectiveDate(),terms.getCreatedAt());
  }
}
