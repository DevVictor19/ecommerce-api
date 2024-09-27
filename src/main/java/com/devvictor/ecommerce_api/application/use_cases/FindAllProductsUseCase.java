package com.devvictor.ecommerce_api.application.use_cases;

import com.devvictor.ecommerce_api.application.dtos.input.FindAllProductsInputDTO;
import com.devvictor.ecommerce_api.application.services.ProductService;
import com.devvictor.ecommerce_api.domain.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindAllProductsUseCase {
    private final ProductService productService;

    public Page<Product> execute(FindAllProductsInputDTO dto) {
        return productService.findAll(dto.name(), dto.page(), dto.size());
    }
}
