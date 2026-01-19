package com.mfuras.booking.kafka.bookings;

public record Customer(
        String id,
        String name,
        String email
) {
}
