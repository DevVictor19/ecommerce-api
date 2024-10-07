package com.devvictor.ecommerce_api.infra.gateways.asaas.dtos;

import jakarta.validation.constraints.NotBlank;

public record ChargeDTO(
        @NotBlank
        String id
) {
}
