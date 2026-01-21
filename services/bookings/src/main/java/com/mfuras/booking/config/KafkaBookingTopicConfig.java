package com.mfuras.booking.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaBookingTopicConfig {
    @Bean
    public NewTopic bookTopic() {
        return TopicBuilder
                .name("book-topic")
                .build();
    }
}
