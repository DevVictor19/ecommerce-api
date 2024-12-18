package com.devvictor.ecommerce_api.products.application.usecases;

import com.devvictor.ecommerce_api.shared.application.exceptions.NotFoundException;
import com.devvictor.ecommerce_api.products.application.services.ProductService;
import com.devvictor.ecommerce_api.products.domain.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UpdateProductUseCase {
    private final ProductService productService;

    public void execute(String productId,
                        long price,
                        String name,
                        String description,
                        String photoUrl,
                        int stockQuantity) {

        Optional<Product> product = productService.findById(productId);

        if (product.isEmpty()) {
            throw new NotFoundException("Product not found");
        }

        Product entity = product.get();
        entity.setPrice(price);
        entity.setName(name);
        entity.setDescription(description);
        entity.setPhotoUrl(photoUrl);
        entity.setStockQuantity(stockQuantity);

        productService.update(entity);
    }
}
