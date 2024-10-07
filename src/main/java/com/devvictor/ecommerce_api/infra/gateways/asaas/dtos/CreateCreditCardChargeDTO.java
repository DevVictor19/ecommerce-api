package com.devvictor.ecommerce_api.infra.gateways.asaas.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record CreateCreditCardChargeDTO(
        @NotBlank
        String customer,

        @NotBlank
        String billingType,

        @NotBlank
        double value,

        @NotBlank
        LocalDate dueDate,

        @Positive
        int installmentCount,

        @NotNull
        CardDTO card
) {
}
