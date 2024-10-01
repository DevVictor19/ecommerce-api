package com.devvictor.ecommerce_api.application.dtos.input;

public record FindAllUserOrdersInputDTO(
        String userId,
        int page,
        int size
) {
}
