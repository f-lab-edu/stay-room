package com.example.base.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@NoArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Seller {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "business_name", nullable = false, length = 100)
  private String businessName;
  @Column(name = "ceo_name", nullable = false, length = 50)
  private String ceoName;
  @Column(name = "business_number", nullable = false, unique = true, length = 20)
  private String businessNumber;
  @Column(nullable = false, unique = true, length = 100)
  private String email;
  @Column(name = "phone_number", length = 20)
  private String phoneNumber;
  @Column(length = 255)
  private String address;
  @CreatedDate
  @Column(name = "created_at", updatable = false, nullable = false)
  private LocalDateTime createdAt;
  @CreatedDate
  @Column(name = "updated_at", updatable = false, nullable = false)
  private LocalDateTime updatedAt;

  @Builder
  public Seller(String businessName, String ceoName, String businessNumber, String email, String phoneNumber, String address) {
    this.businessName = businessName;
    this.ceoName = ceoName;
    this.businessNumber = businessNumber;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.address = address;
  }
}
