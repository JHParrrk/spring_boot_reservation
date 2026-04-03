package com.firstspring.reservation.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    public static final String RESERVATION_EVENTS_TOPIC = "reservation-events";

    @Bean
    public NewTopic reservationEventsTopic() {
        return new NewTopic(RESERVATION_EVENTS_TOPIC, 1, (short) 1);
    }
}
