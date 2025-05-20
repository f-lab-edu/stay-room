package com.example.base.entity;

import com.example.base.enums.AmenityType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public record AmenityId(
    @Column(name = "stay_id")
    Long id,
    AmenityType amenity
) implements Serializable {

}
