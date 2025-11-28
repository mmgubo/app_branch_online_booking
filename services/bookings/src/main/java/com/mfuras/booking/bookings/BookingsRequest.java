package com.mfuras.booking.bookings;

import com.mfuras.booking.product.SelectedProductRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record BookingsRequest(
        Integer id,
        String reference,
        @NotNull(message = "Booking method should be precised")
        BookingsMethod bookingsMethod,
        @NotNull(message = "Customer id should be present")
        @NotEmpty(message = "Customer id should not be empty")
        @NotBlank(message = "Customer id should not be blank")
        String customerId,

        @NotEmpty(message = "You should at least select one product")
        List<SelectedProductRequest> products
) {
}
