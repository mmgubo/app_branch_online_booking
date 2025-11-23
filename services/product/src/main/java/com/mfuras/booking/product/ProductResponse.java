package com.mfuras.booking.product;

public record ProductResponse(
         Integer id,
         String name,
         String description,
         Integer categoryId,
         String categoryName,
         String categoryDescription
) {
}
