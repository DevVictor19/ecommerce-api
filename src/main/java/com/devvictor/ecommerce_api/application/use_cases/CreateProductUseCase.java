package com.devvictor.ecommerce_api.application.use_cases;

import com.devvictor.ecommerce_api.application.dtos.input.CreateProductInputDTO;
import com.devvictor.ecommerce_api.application.exceptions.BadRequestException;
import com.devvictor.ecommerce_api.application.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateProductUseCase {
    private final ProductService productService;

    public void execute(CreateProductInputDTO dto) {
        var existingProduct = productService.findByName(dto.name());

        if (existingProduct.isPresent()) {
            throw new BadRequestException("Product name already exists");
        }

        productService.create(
                dto.price(),
                dto.name(),
                dto.description(),
                dto.photoUrl(),
                dto.stockQuantity()
        );
    }
}
