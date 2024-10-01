package com.devvictor.ecommerce_api.application.dtos.products;

public record UpdateProductInputDTO(
        String productId,
        long price,
        String name,
        String description,
        String photoUrl,
        int stockQuantity
) {
}
