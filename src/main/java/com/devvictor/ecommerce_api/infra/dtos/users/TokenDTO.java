package com.devvictor.ecommerce_api.infra.dtos.users;

import jakarta.validation.constraints.NotBlank;

public record TokenDTO(
        @NotBlank
        String token
) {
}
