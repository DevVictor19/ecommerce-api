package com.devvictor.ecommerce_api.payments.infra.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CardDTO(
        @NotBlank
        @Size(min = 3, max = 55)
        String holderName,

        @NotBlank
        @Size(min = 16, max = 16)
        String number,

        @NotBlank
        @Size(min = 2, max = 2)
        String expiryMonth,

        @NotBlank
        @Size(min = 4, max = 4)
        String expiryYear,

        @NotBlank
        @Size(min = 3, max = 3)
        String ccv
) {
}
