package com.devvictor.ecommerce_api.application.dtos.input;

public record FindAllProductsInputDTO(
        String name,
        int page,
        int size
) {
}
