package com.devvictor.ecommerce_api.application.use_cases.products;

import com.devvictor.ecommerce_api.application.dtos.input.FindAllProductsInputDTO;
import com.devvictor.ecommerce_api.application.dtos.output.ProductOutputDTO;
import com.devvictor.ecommerce_api.application.mappers.ProductEntityMapper;
import com.devvictor.ecommerce_api.application.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindAllProductsUseCase {
    private final ProductService productService;
    private final ProductEntityMapper productEntityMapper;

    public Page<ProductOutputDTO> execute(FindAllProductsInputDTO dto) {
        return productService
                .findAll(dto.name(), dto.page(), dto.size())
                .map(productEntityMapper::toDto);
    }
}
