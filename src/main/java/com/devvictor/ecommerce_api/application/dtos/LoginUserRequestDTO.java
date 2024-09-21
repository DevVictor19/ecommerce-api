package com.devvictor.ecommerce_api.application.dtos;

public record LoginUserRequestDTO(
        String email,
        String password
) {
}
