package com.mfuras.booking.bookingsline;

public record BookingLineRequest(
        Integer id,
        Integer bookingsId,
        Integer productId
    ) {
}
