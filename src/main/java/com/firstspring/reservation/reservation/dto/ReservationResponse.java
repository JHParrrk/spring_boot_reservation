package com.firstspring.reservation.reservation.dto;

import java.time.LocalDateTime;

public record ReservationResponse(Long id, String customerName, int partySize,
        LocalDateTime reservationTime, String status, LocalDateTime createdAt) {
}
