package com.devvictor.ecommerce_api.application.dtos.products;

public record ProductOutputDTO(
        String id,
        long price,
        String name,
        String description,
        String photoUrl,
        int stockQuantity
) {
}
