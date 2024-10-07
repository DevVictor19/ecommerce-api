package com.devvictor.ecommerce_api.infra.gateways.asaas.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CustomerListDTO(
        @NotNull
        List<CustomerDTO> data
) {
}
