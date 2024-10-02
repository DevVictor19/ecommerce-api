package com.devvictor.ecommerce_api.application.use_cases.products;

import com.devvictor.ecommerce_api.application.services.ProductService;
import com.devvictor.ecommerce_api.domain.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindAllProductsUseCase {
    private final ProductService productService;

    public Page<Product> execute(String name, int page, int size) {
        return productService.findAll(name, page, size);
    }
}
