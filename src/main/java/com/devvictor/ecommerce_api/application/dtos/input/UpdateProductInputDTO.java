package com.devvictor.ecommerce_api.application.dtos.input;

public record UpdateProductInputDTO(
        String productId,
        long price,
        String name,
        String description,
        String photoUrl,
        int stockQuantity
) {
}
