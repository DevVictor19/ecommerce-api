package com.devvictor.ecommerce_api.application.dtos.input;

public record UpdateProductInputDTO(
        String productId,
        Double price,
        String name,
        String description,
        String photoUrl,
        Integer stockQuantity
) {
}
