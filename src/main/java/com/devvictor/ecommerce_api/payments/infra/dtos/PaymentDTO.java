package com.devvictor.ecommerce_api.payments.infra.dtos;

import com.devvictor.ecommerce_api.payments.domain.enums.PaymentMethod;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.UUID;

import java.util.Date;

public record PaymentDTO(
        @UUID
        String id,

        @Positive
        long price,

        @NotEmpty
        PaymentMethod method,

        @PositiveOrZero
        int parcels,

        @NotNull
        Date createdAt
) {
}
