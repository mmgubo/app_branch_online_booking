package com.mfuras.booking.kafka;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookProducer {

    private final KafkaTemplate<String, BookConfirmation> kafkaTemplate;

    public void sendBookConfirmation(BookConfirmation bookConfirmation) {
        log.info("Sending book confirmation");
        Message<BookConfirmation> message = MessageBuilder
                .withPayload(bookConfirmation)
                .setHeader(TOPIC, "book-topic")
                .build();
        kafkaTemplate.send(message);
    }

}
