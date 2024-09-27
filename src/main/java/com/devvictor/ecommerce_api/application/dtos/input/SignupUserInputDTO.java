package com.devvictor.ecommerce_api.application.dtos.input;

public record SignupUserInputDTO(
        String username,
        String email,
        String password
) {
}
