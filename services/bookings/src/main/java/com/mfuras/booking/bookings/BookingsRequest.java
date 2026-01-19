package com.mfuras.booking.bookings;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record BookingsRequest(
        Integer id,
        String reference,
        @NotNull(message = "Customer id should be present")
        @NotEmpty(message = "Customer id should not be empty")
        @NotBlank(message = "Customer id should not be blank")
        String customerId
) {
}
