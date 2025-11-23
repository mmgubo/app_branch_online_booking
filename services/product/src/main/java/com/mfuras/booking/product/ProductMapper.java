package com.mfuras.booking.product;

import com.mfuras.booking.category.Category;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toProduct(@Valid ProductRequest request) {
        return Product.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .category(
                        Category.builder()
                                .id(request.categoryId())
                                .build())
                .build();
    }

    public ProductResponse toProduceResponse(Product product) {
        return null;
    }

    public ProductSelectedResponse toProductSelectedResponse(Product product, Integer productId) {
        return new ProductSelectedResponse(
                product.getId(),
                product.getName(),
                product.getDescription()
        );
    }
}
