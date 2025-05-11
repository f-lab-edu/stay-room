package com.example.base.repository;

import com.example.base.entity.TermId;
import com.example.base.entity.Terms;
import com.example.base.enums.TermType;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermsRepository extends JpaRepository<Terms, TermId>, TermsRepositoryCustom {


}
