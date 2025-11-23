package com.mfuras.booking.product;

import jakarta.validation.constraints.NotNull;

public record ProductRequest(
       Integer id,
       @NotNull(message = "Product name is required")
       String name,
       @NotNull(message = "Product description is required")
       String description,
       @NotNull(message = "Product category is required")
       Integer categoryId
) {
}
