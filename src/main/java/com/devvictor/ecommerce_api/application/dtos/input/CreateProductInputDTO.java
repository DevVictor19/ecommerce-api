package com.devvictor.ecommerce_api.application.dtos.input;

public record CreateProductInputDTO(
        long price,
        String name,
        String description,
        String photoUrl,
        int stockQuantity
) {
}
