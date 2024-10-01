package com.devvictor.ecommerce_api.application.dtos.carts;

public record AddProductToCartInputDTO(
        String userId,
        String productId,
        int productQnt
) {
}
