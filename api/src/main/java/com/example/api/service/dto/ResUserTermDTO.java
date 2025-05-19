package com.example.api.service.dto;

import com.example.base.entity.Terms;
import com.example.base.enums.TermType;
import java.time.LocalDate;

public record ResUserTermDTO(TermType id, Long version, String contents, boolean required,
                             LocalDate effectiveDate) {

  public static ResUserTermDTO from(Terms terms) {
    return new ResUserTermDTO(terms.getTermId().id(), terms.getTermId().version(), terms.getContents(), terms.isRequired(), terms.getEffectiveDate());
  }

}
