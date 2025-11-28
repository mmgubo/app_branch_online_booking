package com.mfuras.booking.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaBookingsTopicConfig {

    @Bean
    public NewTopic bookingsTopic() {
        return TopicBuilder
                .name("bookings-topic")
                .build();
    }
}
