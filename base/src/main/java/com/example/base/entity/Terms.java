package com.example.base.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Version;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Terms {
  @EmbeddedId
  private TermId termId;
  private String contents;
  private boolean required;
  @Column(name = "effective_date")
  LocalDate effectiveDate;
  @CreatedDate
  @Column(updatable = false, nullable = false)
  private LocalDateTime createdAt;

  @Builder
  public Terms(TermId termId, String contents, boolean required, LocalDate effectiveDate) {
    this.termId = termId;
    this.contents = contents;
    this.required = required;
    this.effectiveDate = effectiveDate;
  }
}
