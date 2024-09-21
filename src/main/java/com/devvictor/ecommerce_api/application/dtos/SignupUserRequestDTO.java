package com.devvictor.ecommerce_api.application.dtos;

import jakarta.validation.constraints.*;

public record SignupUserRequestDTO(
        @NotBlank
        @Size(min = 3, max = 55)
        String username,

        @Email
        @NotBlank
        String email,

        @NotBlank
        @Size(min = 8, max = 20)
        String password
) {
}
