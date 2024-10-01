package com.devvictor.ecommerce_api.application.use_cases.products;

import com.devvictor.ecommerce_api.application.dtos.input.FindAllProductsInputDTO;
import com.devvictor.ecommerce_api.application.dtos.output.ProductOutputDTO;
import com.devvictor.ecommerce_api.application.services.ProductService;
import com.devvictor.ecommerce_api.domain.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindAllProductsUseCase {
    private final ProductService productService;

    public Page<ProductOutputDTO> execute(FindAllProductsInputDTO dto) {
        return productService
                .findAll(dto.name(), dto.page(), dto.size())
                .map(this::toDto);
    }

    private ProductOutputDTO toDto(Product product) {
        return new ProductOutputDTO(
                product.getId(),
                product.getPrice(),
                product.getName(),
                product.getDescription(),
                product.getPhotoUrl(),
                product.getStockQuantity()
        );
    }
}
