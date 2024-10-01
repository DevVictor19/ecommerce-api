package com.devvictor.ecommerce_api.application.dtos.orders;

import com.devvictor.ecommerce_api.application.dtos.carts.CartOutputDTO;
import com.devvictor.ecommerce_api.domain.enums.OrderStatus;

import java.util.Date;

public record OrderOutputDTO(
        String id,
        String userId,
        OrderStatus status,
        CartOutputDTO cart,
        PaymentOutputDTO payment,
        Date createdAt
) {
}
