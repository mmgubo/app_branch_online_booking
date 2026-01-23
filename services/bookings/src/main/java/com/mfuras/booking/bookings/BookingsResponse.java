package com.mfuras.booking.bookings;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record BookingsResponse(
        Integer id,
        String serviceId,
        String customerId
) {
}
