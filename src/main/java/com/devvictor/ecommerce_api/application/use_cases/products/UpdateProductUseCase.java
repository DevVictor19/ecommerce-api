package com.devvictor.ecommerce_api.application.use_cases.products;

import com.devvictor.ecommerce_api.application.dtos.products.UpdateProductInputDTO;
import com.devvictor.ecommerce_api.application.exceptions.NotFoundException;
import com.devvictor.ecommerce_api.application.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateProductUseCase {
    private final ProductService productService;

    public void execute(UpdateProductInputDTO dto) {
        var product = productService.findById(dto.productId());

        if (product.isEmpty()) {
            throw new NotFoundException("Product not found");
        }

        var entity = product.get();
        entity.setPrice(dto.price());
        entity.setName(dto.name());
        entity.setDescription(dto.description());
        entity.setPhotoUrl(dto.photoUrl());
        entity.setStockQuantity(dto.stockQuantity());

        productService.update(entity);
    }
}
