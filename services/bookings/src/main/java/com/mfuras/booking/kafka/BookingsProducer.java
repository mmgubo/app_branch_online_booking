package com.mfuras.booking.kafka;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingsProducer {

    private final KafkaTemplate<String, BookingsConfirmation> kafkaTemplate;

    public void sendBookingsConfirmation(BookingsConfirmation bookingsConfirmation){
        log.info("Sending bookings confirmation");
        Message<BookingsConfirmation> message = MessageBuilder
                .withPayload(bookingsConfirmation)
                .setHeader(KafkaHeaders.TOPIC, "bookings-topic")
                .build();
        kafkaTemplate.send(message);
    }
}
