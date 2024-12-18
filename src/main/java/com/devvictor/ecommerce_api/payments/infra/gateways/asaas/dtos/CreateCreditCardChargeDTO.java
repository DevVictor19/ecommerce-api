package com.devvictor.ecommerce_api.payments.infra.gateways.asaas.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateCreditCardChargeDTO(
        @NotBlank
        String customer,

        @NotEmpty
        String remoteIp,

        @NotBlank
        String billingType,

        @NotBlank
        double value,

        @NotBlank
        String dueDate,

        @Positive
        int installmentCount,

        @Positive
        double installmentValue,

        @NotNull
        CardDTO card
) {
}
