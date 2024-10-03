package com.devvictor.ecommerce_api.infra.dtos.carts;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.UUID;

import java.util.Date;
import java.util.List;

public record CartDTO(
        @UUID
        String id,

        @NotNull
        List<CartProductDTO> products,

        @Positive
        int productsQuantity,

        @Positive
        long totalPrice,

        @NotNull
        Date createdAt
) {
}
