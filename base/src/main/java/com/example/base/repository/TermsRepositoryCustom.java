package com.example.base.repository;

import com.example.base.dto.SearchTermDTO;
import com.example.base.entity.Terms;
import com.example.base.enums.TermType;
import java.util.List;

public interface TermsRepositoryCustom {
  List<Terms> termsSearchByCondition(SearchTermDTO req);
  List<Terms> activeTermList();
  Terms activeTermInfo(TermType id);

}
