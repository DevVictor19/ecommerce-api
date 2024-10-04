package com.devvictor.ecommerce_api.application.use_cases.products;

import com.devvictor.ecommerce_api.application.exceptions.NotFoundException;
import com.devvictor.ecommerce_api.application.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteProductUseCase {
    private final ProductService productService;

    public void execute(String productId) {
        var product = productService.findById(productId);

        if (product.isEmpty()) {
            throw new NotFoundException("Product not found");
        }

        productService.delete(product.get());
    }
}
