package com.devvictor.ecommerce_api.infra.contracts.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupUserRequest(
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
