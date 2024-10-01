package com.devvictor.ecommerce_api.application.use_cases.products;

import com.devvictor.ecommerce_api.application.dtos.products.DeleteProductInputDTO;
import com.devvictor.ecommerce_api.application.exceptions.NotFoundException;
import com.devvictor.ecommerce_api.application.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteProductUseCase {
    private final ProductService productService;

    public void execute(DeleteProductInputDTO dto) {
        var product = productService.findById(dto.productId());

        if (product.isEmpty()) {
            throw new NotFoundException("Product not found");
        }

        productService.delete(product.get());
    }
}
