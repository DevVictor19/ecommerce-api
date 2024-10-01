package com.devvictor.ecommerce_api.application.dtos.output;

public record CartProductOutputDTO(
        String id,
        long price,
        String description,
        String photoUrl,
        int inCartQuantity
) {
}
