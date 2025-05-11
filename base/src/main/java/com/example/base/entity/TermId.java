package com.example.base.entity;

import com.example.base.enums.TermType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.io.Serializable;
@Embeddable
public record TermId (
    @Enumerated(EnumType.STRING)
    TermType id,
    Long version
    ) implements Serializable {
}
