package com.example.admin.service;

import com.example.admin.service.dto.ReqTermDTO;
import com.example.admin.service.dto.ResTermDTO;
import com.example.admin.service.dto.UpdateReqTermDTO;
import com.example.base.dto.SearchTermDTO;
import com.example.base.entity.TermId;
import com.example.base.entity.Terms;
import com.example.base.enums.ErrorType;
import com.example.base.enums.TermType;
import com.example.base.exception.CommonException;
import com.example.base.repository.TermsRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TermService {

  private final TermsRepository termsRepository;

  public void createTerm(ReqTermDTO req) {
    Terms term = Terms.builder().termId(new TermId(req.id(), 1L))
        .contents(req.contents()).required(req.required()).effectiveDate(req.effectiveDate()).build();

    termsRepository.save(term);
  }

  public List<ResTermDTO> getTermsList(SearchTermDTO req) {
    return termsRepository.termsSearchByCondition(req).stream()
        .map(ResTermDTO::from)
        .toList();
  }

  @Transactional
  public void updateTerm(TermType id, Long version, UpdateReqTermDTO req) {
    TermId updateTermId = new TermId(id, version + 1);
    termsRepository.findById(new TermId(id, version)).orElseThrow(() -> new CommonException(ErrorType.BAD_REQUEST));

    // 버전업 데이터 insert
    Terms versionUpTerm = Terms.builder().termId(updateTermId)
        .contents(req.contents())
        .required(req.required())
        .effectiveDate(req.effectiveDate()).build();

    try {
      termsRepository.save(versionUpTerm);
    } catch (EntityExistsException e) {
      throw new CommonException(ErrorType.OPTIMISTIC_LOCK_CONFLICT);
    }

  }

}
