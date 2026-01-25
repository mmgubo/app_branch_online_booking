package com.mfuras.booking.bookings;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.TimeZone;

public record BookingsRequest(
        Integer id,
        String service,
        String customerId,
        String date,
        String time,
        String status,
        String branch,
        String notes
) {
}
