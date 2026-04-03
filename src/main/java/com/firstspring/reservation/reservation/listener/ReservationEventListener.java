package com.firstspring.reservation.reservation.listener;

import com.firstspring.reservation.config.KafkaConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Kafka (9092 포트, Zookeeper 2181 포트가 내부 관리) 리스너
 * - 예약 이벤트 스트림을 수신하여 로깅/분석 용도로 사용합니다.
 * - 실제로는 통계, 대시보드, 데이터 파이프라인 등에 활용됩니다.
 */
@Slf4j
@Component
public class ReservationEventListener {

    @KafkaListener(topics = KafkaConfig.RESERVATION_EVENTS_TOPIC)
    public void handleEvent(String event) {
        log.info("[Kafka 이벤트 수신] {}", event);
    }
}
