package com.example.base.entity;

import com.example.base.enums.LocationType;
import com.example.base.enums.StayType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "stay_info")
public class StayInfo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  @Enumerated(EnumType.STRING)
  private LocationType location;
  private String description;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "seller_id")
  private Seller seller;
  @Column(name = "stay_type")
  @Enumerated(EnumType.STRING)
  private StayType stayType;
  @CreatedDate
  @Column(name = "created_at", updatable = false, nullable = false)
  private LocalDateTime createdAt;
  @CreatedDate
  @Column(name = "updated_at", updatable = false, nullable = false)
  private LocalDateTime updatedAt;

  @Builder
  public StayInfo(String name, LocationType location, String description, Seller seller, StayType stayType) {
    this.name = name;
    this.location = location;
    this.description = description;
    this.seller = seller;
    this.stayType = stayType;
  }
}
