package com.devvictor.ecommerce_api.application.dtos.output;

import java.util.List;

public record CartOutputDTO(
        String id,
        List<CartProductOutputDTO> products,
        int productsQuantity,
        double totalPrice
) {
}
