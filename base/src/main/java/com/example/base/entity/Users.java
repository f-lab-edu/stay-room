package com.example.base.entity;

import com.example.base.enums.GenderType;
import com.example.base.enums.RoleType;
import com.example.base.enums.UserProviderType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
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
public class Users {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String email;
  private String password;
  private LocalDate birthday;
  @Enumerated(EnumType.STRING)
  private GenderType gender;
  @Column(name = "display_name")
  private String displayName;
  @Enumerated(EnumType.STRING)
  private UserProviderType provider;
  @Enumerated(EnumType.STRING)
  private RoleType role;
  @CreatedDate
  @Column(name = "created_at", updatable = false, nullable = false)
  private LocalDateTime createdAt;
  @CreatedDate
  @Column(name = "updated_at", updatable = false, nullable = false)
  private LocalDateTime updatedAt;
  @Column(name = "delete_yn")
  private boolean deleteYn;


  @Builder
  public Users(String email, String password, LocalDate birthday, GenderType gender, String displayName, UserProviderType provider,
      RoleType role, boolean deleteYn) {
    this.email = email;
    this.password = password;
    this.birthday = birthday;
    this.gender = gender;
    this.displayName = displayName;
    this.provider = provider;
    this.role = role;
    this.deleteYn = deleteYn;
  }
}
