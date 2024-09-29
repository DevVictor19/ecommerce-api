package com.devvictor.ecommerce_api.infra.contracts.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.UUID;

public record SubtractProductFromCartRequest(
        @UUID
        String productId,

        @Positive
        @Max(30)
        int quantity
) {
}
