package com.devvictor.ecommerce_api.application.dtos.carts;

public record SubtractProductFromCartInputDTO(
        String userId,
        String productId,
        int productQnt
) {
}
