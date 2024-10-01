package com.devvictor.ecommerce_api.application.dtos.carts;

public record CartProductOutputDTO(
        String id,
        long price,
        String description,
        String photoUrl,
        int inCartQuantity
) {
}
