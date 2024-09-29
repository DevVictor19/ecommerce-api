package com.devvictor.ecommerce_api.application.dtos.output;

public record CartProductOutputDTO(
        String id,
        double price,
        String description,
        String photoUrl,
        int inCartQuantity
) {
}
