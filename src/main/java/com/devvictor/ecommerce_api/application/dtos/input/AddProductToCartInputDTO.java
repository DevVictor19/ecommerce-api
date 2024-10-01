package com.devvictor.ecommerce_api.application.dtos.input;

public record AddProductToCartInputDTO(
        String userId,
        String productId,
        int productQnt
) {
}
