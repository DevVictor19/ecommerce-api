package com.devvictor.ecommerce_api.payments.infra.gateways.asaas.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCustomerDTO(
        @NotBlank
        @Size(min = 3, max = 55)
        String name,

        @Email
        @NotBlank
        String email,

        @NotBlank
        @Size(min = 11, max = 14)
        String document
) {
}
