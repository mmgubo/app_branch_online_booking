package com.mfuras.booking.kafka.bookings;

import java.util.List;

public record BookingsConfirmation(
        String bookingsReference,
        BookingsMethod bookingsMethod,
        Customer customer
) {
}
