package com.devvictor.ecommerce_api.products.application.usecases;

import com.devvictor.ecommerce_api.products.application.services.ProductService;
import com.devvictor.ecommerce_api.products.domain.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindAllProductsUseCase {
    private final ProductService productService;

    public Page<Product> execute(int page,
                                 int size,
                                 String sort,
                                 String sortBy,
                                 String name) {

        return productService.findAll(page, size, sort, sortBy, name);
    }
}
