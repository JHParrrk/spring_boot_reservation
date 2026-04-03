package com.firstspring.reservation.reservation.listener;

import com.firstspring.reservation.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * RabbitMQ (5672 포트) 리스너
 * - 예약이 생성되면 알림 메시지를 수신하여 처리합니다.
 * - 실제로는 SMS, 이메일, 푸시 알림 등을 발송하는 역할을 합니다.
 */
@Slf4j
@Component
public class NotificationListener {

    @RabbitListener(queues = RabbitConfig.NOTIFICATION_QUEUE)
    public void handleNotification(String message) {
        log.info("[RabbitMQ 알림 수신] {}", message);
    }
}
