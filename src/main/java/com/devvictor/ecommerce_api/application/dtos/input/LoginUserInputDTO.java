package com.devvictor.ecommerce_api.application.dtos.input;

public record LoginUserInputDTO(
        String email,
        String password
) {
}
