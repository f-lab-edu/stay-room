package com.example.base.entity;

import com.example.base.enums.ReservationStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Reservation {
  @EmbeddedId
  private ReservationId reservationId;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "room_id")
  private StayRoom room;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private Users user;
  @Enumerated(EnumType.STRING)
  private ReservationStatus status;
  @CreatedDate
  @Column(name = "created_at", updatable = false, nullable = false)
  private LocalDateTime createdAt;
  @CreatedDate
  @Column(name = "updated_at", updatable = false, nullable = false)
  private LocalDateTime updatedAt;

}
