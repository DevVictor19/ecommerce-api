package com.devvictor.ecommerce_api.payments.domain.vo;

public record CardVO(
        String holderName,
        String number,
        String expiryMonth,
        String expiryYear,
        String ccv
) {
}
