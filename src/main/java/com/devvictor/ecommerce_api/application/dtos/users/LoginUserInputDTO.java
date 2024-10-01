package com.devvictor.ecommerce_api.application.dtos.users;

public record LoginUserInputDTO(
        String email,
        String password
) {
}
