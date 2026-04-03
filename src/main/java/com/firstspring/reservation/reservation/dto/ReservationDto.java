package com.firstspring.reservation.reservation.dto;

import java.time.LocalDateTime;

public record ReservationDto(String customerName, int partySize, LocalDateTime reservationTime) {
}
