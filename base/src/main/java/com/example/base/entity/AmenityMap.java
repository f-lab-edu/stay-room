package com.example.base.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "stay_amenity_map")
public class AmenityMap {
  @EmbeddedId
  private AmenityId amenityId;
}
