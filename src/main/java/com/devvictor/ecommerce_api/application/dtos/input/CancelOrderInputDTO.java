package com.devvictor.ecommerce_api.application.dtos.input;

public record CancelOrderInputDTO(
        String orderId,
        String userId
) {
}
