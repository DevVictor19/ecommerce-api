package com.devvictor.ecommerce_api.infra.dtos.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginUserDTO(
        @NotBlank
        @Email
        String email,

        @NotBlank
        @Size(min = 8, max = 20)
        String password
) {
}
