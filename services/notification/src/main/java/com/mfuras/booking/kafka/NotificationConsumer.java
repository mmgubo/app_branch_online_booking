package com.mfuras.booking.kafka;

import com.mfuras.booking.email.EmailService;
import com.mfuras.booking.kafka.booking.BookingConfirmation;
import com.mfuras.booking.kafka.branch.BranchConfirmation;
import com.mfuras.booking.notification.Notification;
import com.mfuras.booking.notification.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.mfuras.booking.notification.NotificationType.BOOKING_CONFIRMATION;
import static com.mfuras.booking.notification.NotificationType.BRANCH_CONFIRMATION;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j

public class NotificationConsumer {
    private final NotificationRepository repository;
    private final EmailService emailService;

    @KafkaListener(topics = "branch-topic")
    public void consumeBranchConfirmationNotification(BranchConfirmation branchConfirmation) throws MessagingException {
        log.info(format("Consuming the message from branch-topic Topic:: %s", branchConfirmation));
        repository.save(
                Notification.builder()
                .type(BRANCH_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .branchConfirmation(branchConfirmation)
                .build()
        );

        //send email to the clients for branch confirmation
        var customerName = branchConfirmation.customerName();
        emailService.sendBranchConfirmation(
                branchConfirmation.customerEmail(),
                customerName,
                branchConfirmation.bookingsReference()
        );

    }

    @KafkaListener(topics = "book-topic")
    public void consumeBookingConfirmationNotification(BookingConfirmation bookingConfirmation) throws MessagingException {
        log.info(format("Consuming the message from bookings-topic Topic:: %s", bookingConfirmation));
        repository.save(
                Notification.builder()
                        .type(BOOKING_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .bookingConfirmation(bookingConfirmation)
                        .build()
        );

        //send email to the clients for booking confirmation
        var customerName = bookingConfirmation.customer().name();
        emailService.sendBookingConfirmation(
                bookingConfirmation.customer().email(),
                customerName,
                bookingConfirmation.bookingsReference()
        );
    }
}
