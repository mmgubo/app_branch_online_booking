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
        @NotNull(message = "Customer id should be present")
        @NotEmpty(message = "Customer id should not be empty")
        @NotBlank(message = "Customer id should not be blank")
        String customerId,
        @NotBlank(message = "Date should not be blank")
        String date,
        String time,
        String status,
        String branch,
        String notes
) {
}
