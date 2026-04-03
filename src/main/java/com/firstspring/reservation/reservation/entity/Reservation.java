package com.firstspring.reservation.reservation.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private int partySize;
    private LocalDateTime reservationTime;
    private String status; // PENDING, CONFIRMED, CANCELLED
    private LocalDateTime createdAt;

    @Builder
    public Reservation(String customerName, int partySize, LocalDateTime reservationTime) {
        this.customerName = customerName;
        this.partySize = partySize;
        this.reservationTime = reservationTime;
        this.status = "PENDING";
        this.createdAt = LocalDateTime.now();
    }

    public void confirm() {
        this.status = "CONFIRMED";
    }

    public void cancel() {
        this.status = "CANCELLED";
    }
}
