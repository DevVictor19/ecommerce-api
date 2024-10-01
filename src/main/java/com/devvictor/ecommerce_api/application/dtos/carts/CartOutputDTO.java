package com.devvictor.ecommerce_api.application.dtos.carts;

import java.util.List;

public record CartOutputDTO(
        String id,
        List<CartProductOutputDTO> products,
        int productsQuantity,
        long totalPrice
) {
}
