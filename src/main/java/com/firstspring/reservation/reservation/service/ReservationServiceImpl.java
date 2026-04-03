package com.firstspring.reservation.reservation.service;

import com.firstspring.reservation.config.KafkaConfig;
import com.firstspring.reservation.config.RabbitConfig;
import com.firstspring.reservation.reservation.dto.ReservationDto;
import com.firstspring.reservation.reservation.dto.ReservationResponse;
import com.firstspring.reservation.reservation.entity.Reservation;
import com.firstspring.reservation.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    // [MariaDB - 3307] JPA를 통한 데이터 영구 저장
    private final ReservationRepository reservationRepository;

    // [Redis - 6379] 조회 결과 캐싱
    private final RedisTemplate<String, String> redisTemplate;

    // [RabbitMQ - 5672] 알림 메시지 발송
    private final RabbitTemplate rabbitTemplate;

    // [Kafka - 9092] 이벤트 스트리밍 발행 (Zookeeper 2181이 내부 관리)
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public ReservationResponse createReservation(ReservationDto dto) {
        // 1) MariaDB에 예약 저장
        Reservation reservation = Reservation.builder()
                .customerName(dto.customerName())
                .partySize(dto.partySize())
                .reservationTime(dto.reservationTime())
                .build();
        Reservation saved = reservationRepository.save(reservation);
        log.info("[MariaDB] 예약 저장 완료 - id: {}", saved.getId());

        // 2) Redis에 캐시 저장 (다음 조회 시 DB를 안 거치도록)
        String cacheKey = "reservation:" + saved.getId();
        redisTemplate.opsForValue().set(cacheKey, saved.getCustomerName(), Duration.ofMinutes(10));
        log.info("[Redis] 캐시 저장 완료 - key: {}", cacheKey);

        // 3) RabbitMQ로 알림 메시지 전송 (예: 고객에게 SMS/이메일 발송)
        String notificationMessage = String.format("예약 알림: %s님 %d명, %s",
                saved.getCustomerName(), saved.getPartySize(), saved.getReservationTime());
        rabbitTemplate.convertAndSend(RabbitConfig.NOTIFICATION_QUEUE, notificationMessage);
        log.info("[RabbitMQ] 알림 발송 완료");

        // 4) Kafka로 이벤트 발행 (예: 통계/분석 시스템으로 전달)
        String eventMessage = String.format("{\"event\":\"CREATED\",\"id\":%d,\"customer\":\"%s\"}",
                saved.getId(), saved.getCustomerName());
        kafkaTemplate.send(KafkaConfig.RESERVATION_EVENTS_TOPIC, eventMessage);
        log.info("[Kafka] 이벤트 발행 완료");

        return toResponse(saved);
    }

    @Override
    public List<ReservationResponse> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationResponse getReservation(Long id) {
        // Redis 캐시 먼저 확인
        String cacheKey = "reservation:" + id;
        String cached = redisTemplate.opsForValue().get(cacheKey);
        if (cached != null) {
            log.info("[Redis] 캐시 히트! - key: {}", cacheKey);
        } else {
            log.info("[Redis] 캐시 미스 → MariaDB 조회");
        }

        // MariaDB에서 조회
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("예약을 찾을 수 없습니다. id: " + id));
        return toResponse(reservation);
    }

    @Override
    public void cancelReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("예약을 찾을 수 없습니다. id: " + id));
        reservation.cancel();
        reservationRepository.save(reservation);

        // Redis 캐시 삭제
        redisTemplate.delete("reservation:" + id);
        log.info("[Redis] 캐시 삭제 - id: {}", id);

        // RabbitMQ 취소 알림
        rabbitTemplate.convertAndSend(RabbitConfig.NOTIFICATION_QUEUE,
                String.format("예약 취소 알림: %s님의 예약이 취소되었습니다.", reservation.getCustomerName()));
        log.info("[RabbitMQ] 취소 알림 발송");

        // Kafka 취소 이벤트
        kafkaTemplate.send(KafkaConfig.RESERVATION_EVENTS_TOPIC,
                String.format("{\"event\":\"CANCELLED\",\"id\":%d}", id));
        log.info("[Kafka] 취소 이벤트 발행");
    }

    private ReservationResponse toResponse(Reservation reservation) {
        return new ReservationResponse(
                reservation.getId(),
                reservation.getCustomerName(),
                reservation.getPartySize(),
                reservation.getReservationTime(),
                reservation.getStatus(),
                reservation.getCreatedAt());
    }
}
