package com.mfuras.booking.product;

public record SelectedProductResponse(
        Integer productId,
        String name,
        String description
) {
}
