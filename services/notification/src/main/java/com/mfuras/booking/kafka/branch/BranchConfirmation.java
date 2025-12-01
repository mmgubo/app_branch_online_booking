package com.mfuras.booking.kafka.branch;

import com.mfuras.booking.kafka.bookings.BookingsMethod;

public record BranchConfirmation(
        String bookingsReference,
        BookingsMethod bookingsMethod,
        String customerFirstname,
        String customerLastname,
        String customerEmail
) {
}
