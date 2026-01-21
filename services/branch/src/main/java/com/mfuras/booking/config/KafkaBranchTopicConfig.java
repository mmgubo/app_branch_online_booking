package com.mfuras.booking.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaBranchTopicConfig {

    @Bean
    public NewTopic branchTopic() {
        return TopicBuilder
                .name("branch-topic")
                .build();
    }
}
