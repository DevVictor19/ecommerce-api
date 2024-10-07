package com.devvictor.ecommerce_api.infra.gateways.asaas.dtos;

import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public record CardDTO(
        @NotEmpty
        @Length(min = 3)
        String holderName,

        @NotEmpty
        @Length(min = 16, max = 16)
        String number,

        @NotEmpty
        @Length(min = 2, max = 2)
        String expiryMonth,

        @NotEmpty
        @Length(min = 4, max = 4)
        String expiryYear,

        @NotEmpty
        @Length(min = 3, max = 3)
        String cvv
) {
}
