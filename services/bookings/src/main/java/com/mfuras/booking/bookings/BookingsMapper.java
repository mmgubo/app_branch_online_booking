package com.mfuras.booking.bookings;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class BookingsMapper {
    public Bookings toBookings(@Valid BookingsRequest request) {
        return Bookings.builder()
                .id(request.id())
                .customerId(request.customerId())
                .reference(request.reference())
                .bookingMethod(request.bookingsMethod())
                .build();
    }

    public BookingsResponse fromBooking(Bookings bookings) {
        return new BookingsResponse(
                bookings.getId(),
                bookings.getReference(),
                bookings.getBookingMethod(),
                bookings.getCustomerId()
        );
    }
}
