package com.devvictor.ecommerce_api.payments.infra.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record CreateCreditCardChargeDTO(
        @NotBlank
        @Size(min = 11, max = 11)
        String document,

        @PositiveOrZero
        int parcels,

        @NotNull
        @Valid
        CardDTO card
) {
}
