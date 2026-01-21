package com.mfuras.booking.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {

    private final KafkaTemplate<String, BranchNotificationRequest> kafkaTemplate;

    public void sendNotification(BranchNotificationRequest request) {
        log.info("Sending notification with body = < {} >", request);
        Message<BranchNotificationRequest> message = MessageBuilder
                .withPayload(request)
                .setHeader(TOPIC, "branch-topic")
                .build();

        kafkaTemplate.send(message);

    }
}
