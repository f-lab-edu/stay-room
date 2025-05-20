package com.example.base.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public record ReservationId(
    @Column(name = "room_type_id")
    Long roomTypeId,
    @Column(name = "reservation_date")
    LocalDate reservationDate
) implements Serializable {

}
