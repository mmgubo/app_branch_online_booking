package com.mfuras.booking.bookings;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record BookingsResponse(
        Integer id,
        String service,
        String customerId,
        String branch,
        String date,
        String time,
        String status,
        String notes) {
}
