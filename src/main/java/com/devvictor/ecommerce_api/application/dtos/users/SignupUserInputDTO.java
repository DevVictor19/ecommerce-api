package com.devvictor.ecommerce_api.application.dtos.users;

public record SignupUserInputDTO(
        String username,
        String email,
        String password
) {
}
