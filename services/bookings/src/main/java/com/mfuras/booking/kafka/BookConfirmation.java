package com.mfuras.booking.kafka;

import com.mfuras.booking.customer.CustomerResponse;

public record BookConfirmation(
        String bookingsReference,
        CustomerResponse customer
) {
}
