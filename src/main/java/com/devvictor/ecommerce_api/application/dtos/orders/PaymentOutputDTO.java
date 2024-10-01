package com.devvictor.ecommerce_api.application.dtos.orders;

import com.devvictor.ecommerce_api.domain.enums.PaymentMethod;

import java.util.Date;

public record PaymentOutputDTO(
        String id,
        long price,
        PaymentMethod method,
        int parcels,
        Date createdAt
) {
}
