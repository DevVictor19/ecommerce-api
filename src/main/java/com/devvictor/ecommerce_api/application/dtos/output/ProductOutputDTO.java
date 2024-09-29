package com.devvictor.ecommerce_api.application.dtos.output;

public record ProductOutputDTO(
        String id,
        double price,
        String name,
        String description,
        String photoUrl,
        int stockQuantity
) {
}
