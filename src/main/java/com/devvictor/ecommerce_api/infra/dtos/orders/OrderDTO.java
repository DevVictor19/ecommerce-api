package com.devvictor.ecommerce_api.infra.dtos.orders;

import com.devvictor.ecommerce_api.domain.enums.OrderStatus;
import com.devvictor.ecommerce_api.infra.dtos.carts.CartDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UUID;

import java.util.Date;

public record OrderDTO(
        @UUID
        String id,

        @NotBlank
        OrderStatus status,

        @NotNull
        CartDTO cart,

        @NotNull
        PaymentDTO payment,

        @NotNull
        Date createdAt
) {
}
