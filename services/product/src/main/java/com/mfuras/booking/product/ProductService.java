package com.mfuras.booking.product;

import com.mfuras.booking.exception.ProductSelectedException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public @Nullable Integer createProduct(@Valid ProductRequest request) {
        var product = mapper.toProduct(request);
        return repository.save(product).getId();
    }

    public @Nullable List<ProductSelectedResponse> selectedProducts(List<ProductSelectedRequest> request) {
        var productIds = request
                .stream()
                .map(ProductSelectedRequest::productId)
                .toList();
        var storedProducts = repository.findAllByIdInOrderById(productIds);
        if (storedProducts.size() != productIds.size()) {
            throw new ProductSelectedException("One or more products does not exists");
        }
        var storedRequest = request
                .stream()
                .sorted(Comparator.comparing(ProductSelectedRequest::productId))
                .toList();
        var selectedProducts = new ArrayList<ProductSelectedResponse>();
        for (int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = storedRequest.get(i);
            if(product.getId() < productRequest.productId()){
                throw new ProductSelectedException("Product order does not match:: " + productRequest.productId());
            }
            repository.save(product);
            selectedProducts.add(mapper.toProductSelectedResponse(product, productRequest.productId()));
        }
        return selectedProducts;
    }

    public @Nullable ProductResponse findById(Integer productId) {
        return repository.findById(productId)
                .map(mapper::toProduceResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID:: " + productId));
    }

    public @Nullable List<ProductResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toProduceResponse)
                .collect(Collectors.toList());
    }
}
