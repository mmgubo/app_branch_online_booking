package com.mfuras.booking.product;

import jakarta.validation.constraints.NotNull;

public record ProductSelectedRequest(
        @NotNull(message = "Product is mandatory")
        Integer productId
) {

}
