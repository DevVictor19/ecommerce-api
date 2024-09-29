package com.devvictor.ecommerce_api.application.dtos.input;

public record SubtractProductFromCartInputDTO(
        String userId,
        String productId,
        int productQnt
) {
}
