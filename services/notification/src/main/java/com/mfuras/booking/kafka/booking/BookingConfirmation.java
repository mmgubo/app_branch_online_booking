package com.mfuras.booking.kafka.booking;

public record BookingConfirmation(
        String bookingsReference,
        Customer customer
) {
}
