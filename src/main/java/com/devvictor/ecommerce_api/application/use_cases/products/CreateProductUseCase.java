package com.devvictor.ecommerce_api.application.use_cases.products;

import com.devvictor.ecommerce_api.application.exceptions.BadRequestException;
import com.devvictor.ecommerce_api.application.services.ProductService;
import com.devvictor.ecommerce_api.domain.entities.Product;
import com.devvictor.ecommerce_api.domain.factories.ProductFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateProductUseCase {
    private final ProductService productService;

    public void execute(long price,
                        String name,
                        String description,
                        String photoUrl,
                        int stockQuantity) {

        var existingProduct = productService.findByName(name);

        if (existingProduct.isPresent()) {
            throw new BadRequestException("Product name already exists");
        }

        Product product = ProductFactory.create(
                price,
                name,
                description,
                photoUrl,
                stockQuantity
        );

        productService.create(product);
    }
}
