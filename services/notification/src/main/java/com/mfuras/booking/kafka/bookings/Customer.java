package com.mfuras.booking.kafka.bookings;

public record Customer(
        String id,
        String firstname,
        String lastname,
        String email
) {
}
