package com.devvictor.ecommerce_api.infra.gateways.asaas.dtos;

import jakarta.validation.constraints.NotBlank;

public record CustomerDTO(
        @NotBlank
        String id
) {
}
