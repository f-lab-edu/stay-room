package com.example.api.service;

import com.example.api.service.dto.ResUserTermDTO;
import com.example.base.entity.Terms;
import com.example.base.enums.TermType;
import com.example.base.repository.TermsRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserTermService {

  private final TermsRepository termsRepository;

  public List<ResUserTermDTO> getActiveTermList() {
    return termsRepository.activeTermList(null).stream()
        .map(ResUserTermDTO::from)
        .toList();
  }

  public ResUserTermDTO getActiveTermInfo(TermType id) {
    Terms terms = termsRepository.activeTermInfo(id);
    return ResUserTermDTO.from(terms);
  }

}
