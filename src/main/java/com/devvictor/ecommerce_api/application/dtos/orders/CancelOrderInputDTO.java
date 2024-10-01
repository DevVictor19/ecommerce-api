package com.devvictor.ecommerce_api.application.dtos.orders;

public record CancelOrderInputDTO(
        String orderId,
        String userId
) {
}
