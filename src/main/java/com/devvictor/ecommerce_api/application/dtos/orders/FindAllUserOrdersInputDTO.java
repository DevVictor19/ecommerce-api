package com.devvictor.ecommerce_api.application.dtos.orders;

public record FindAllUserOrdersInputDTO(
        String userId,
        int page,
        int size
) {
}
