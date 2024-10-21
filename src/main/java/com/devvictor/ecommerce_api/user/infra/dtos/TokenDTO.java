package com.devvictor.ecommerce_api.user.infra.dtos;

import jakarta.validation.constraints.NotBlank;

public record TokenDTO(
        @NotBlank
        String token
) {
}
