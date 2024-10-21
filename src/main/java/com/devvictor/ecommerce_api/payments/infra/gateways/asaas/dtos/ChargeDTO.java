package com.devvictor.ecommerce_api.payments.infra.gateways.asaas.dtos;

import jakarta.validation.constraints.NotBlank;

public record ChargeDTO(
        @NotBlank
        String id
) {
}
