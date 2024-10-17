package com.devvictor.ecommerce_api.infra.gateways.asaas.dtos;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CardDTO(
        @NotBlank
        @Length(min = 3)
        String holderName,

        @NotBlank
        @Length(min = 16, max = 16)
        String number,

        @NotBlank
        @Length(min = 2, max = 2)
        String expiryMonth,

        @NotBlank
        @Length(min = 4, max = 4)
        String expiryYear,

        @NotBlank
        @Length(min = 3, max = 3)
        String ccv
) {
}
