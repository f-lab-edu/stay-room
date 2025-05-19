package com.example.base.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class UserTerm {
  @EmbeddedId
  private UserTermId id;
  private boolean agreed;
  @Column(name = "agreed_at")
  private LocalDateTime agreedAt;
  @CreatedDate
  @Column(name = "created_at", updatable = false, nullable = false)
  private LocalDateTime createdAt;
  @CreatedDate
  @Column(name = "updated_at", updatable = false, nullable = false)
  private LocalDateTime updatedAt;

  public UserTerm(UserTermId id, boolean agreed) {
    this.id = id;
    this.agreed = agreed;
    this.agreedAt = agreed ? LocalDateTime.now() : null;
  }
}
