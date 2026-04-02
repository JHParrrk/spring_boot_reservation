package com.firstspring.reservation.reservation.dto;

import java.time.LocalDateTime;

public record ReservationResponse(Long id, String title, String content, String author, LocalDateTime createdAt) {
}
