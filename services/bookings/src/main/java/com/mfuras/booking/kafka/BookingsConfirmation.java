package com.mfuras.booking.kafka;

import com.mfuras.booking.bookings.BookingsMethod;
import com.mfuras.booking.customer.CustomerResponse;
import com.mfuras.booking.product.SelectedProductResponse;

import java.util.List;

public record BookingsConfirmation(
        String bookingsReference,
        BookingsMethod bookingsMethod,
        CustomerResponse customer,
        List<SelectedProductResponse> products
) {
}
