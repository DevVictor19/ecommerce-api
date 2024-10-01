package com.devvictor.ecommerce_api.application.dtos.products;

public record FindAllProductsInputDTO(
        String name,
        int page,
        int size
) {
}
