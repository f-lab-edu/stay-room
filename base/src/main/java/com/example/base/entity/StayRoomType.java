package com.example.base.entity;

import com.example.base.entity.StayInfo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
public class StayRoomType {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "stay_id")
  private StayInfo stayInfo;
  private String roomType;
  private int count;
  private int price;
  @CreatedDate
  @Column(name = "created_at", updatable = false, nullable = false)
  private LocalDateTime createdAt;
  @CreatedDate
  @Column(name = "updated_at", updatable = false, nullable = false)
  private LocalDateTime updatedAt;

  @Builder
  public StayRoomType(StayInfo stayInfo, String roomType, int count, int price) {
    this.stayInfo = stayInfo;
    this.roomType = roomType;
    this.count = count;
    this.price = price;
  }
}
