package com.example.base.dto;

import com.example.base.enums.SortType;
import com.example.base.enums.TermType;

public record SearchTermDTO(TermType id,
                            SortBy sortBy,
                            SortType sortType,
                            int page,
                            int size) {

  public enum SortBy { VERSION, EFFECTIVE_DATE, CREATE_AT }
}
