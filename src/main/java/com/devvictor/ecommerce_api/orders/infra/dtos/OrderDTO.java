package com.devvictor.ecommerce_api.orders.infra.dtos;

import com.devvictor.ecommerce_api.orders.domain.enums.OrderStatus;
import com.devvictor.ecommerce_api.cart.infra.dtos.CartDTO;
import com.devvictor.ecommerce_api.payments.infra.dtos.PaymentDTO;
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
