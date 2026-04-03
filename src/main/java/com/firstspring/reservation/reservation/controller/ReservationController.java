package com.firstspring.reservation.reservation.controller;

import com.firstspring.reservation.reservation.dto.ReservationDto;
import com.firstspring.reservation.reservation.dto.ReservationResponse;
import com.firstspring.reservation.reservation.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
@Tag(name = "Reservation", description = "식당 예약 관리 API")
public class ReservationController {

    private final ReservationService reservationService;

    // POST /reservations → MariaDB 저장 + Redis 캐싱 + RabbitMQ 알림 + Kafka 이벤트
    @PostMapping
    @Operation(summary = "예약 생성", description = "새로운 예약을 생성하고 DB 저장, 캐싱, 메시지 발행을 수행합니다.")
    public ReservationResponse createReservation(@RequestBody ReservationDto dto) {
        return reservationService.createReservation(dto);
    }

    // GET /reservations → MariaDB 전체 조회
    @GetMapping
    @Operation(summary = "예약 전체 조회", description = "데이터베이스에 저장된 모든 예약 목록을 조회합니다.")
    public List<ReservationResponse> getAllReservations() {
        return reservationService.getAllReservations();
    }

    // GET /reservations/{id} → Redis 캐시 확인 → 없으면 MariaDB 조회
    @GetMapping("/{id}")
    @Operation(summary = "단일 예약 조회", description = "ID를 통해 특정 예약을 조회합니다. Redis 캐시를 먼저 확인합니다.")
    public ReservationResponse getReservation(@PathVariable Long id) {
        return reservationService.getReservation(id);
    }

    // DELETE /reservations/{id}/cancel → 취소 처리 + Redis 삭제 + RabbitMQ 알림 + Kafka 이벤트
    @DeleteMapping("/{id}/cancel")
    @Operation(summary = "예약 취소", description = "특정 예약을 취소 상태로 변경하고 관련 리소스를 정리합니다.")
    public String cancelReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return "예약이 취소되었습니다.";
    }
}
