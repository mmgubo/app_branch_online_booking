package com.mfuras.booking.bookings;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class BookingsMapper {
    public Bookings toBookings(@Valid BookingsRequest request) {
        return Bookings.builder()
                .id(request.id())
                .customerId(request.customerId())
                .service(request.service())
                .branch(request.branch())
                .status(request.status())
                .time(request.time())
                .date(request.date())
                .notes(request.notes())
                .build();
    }

    public BookingsResponse fromBooking(Bookings bookings) {
        return new BookingsResponse(
                bookings.getId(),
                bookings.getService(),
                bookings.getCustomerId()
        );
    }
}
