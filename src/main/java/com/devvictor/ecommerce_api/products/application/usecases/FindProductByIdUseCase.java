package com.devvictor.ecommerce_api.products.application.usecases;

import com.devvictor.ecommerce_api.products.application.services.ProductService;
import com.devvictor.ecommerce_api.products.domain.entities.Product;
import com.devvictor.ecommerce_api.shared.application.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindProductByIdUseCase {
    private final ProductService productService;

    public Product execute(String productId) {
        return productService.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found"));
    }
}
