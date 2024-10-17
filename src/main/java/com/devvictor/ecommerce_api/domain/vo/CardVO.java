package com.devvictor.ecommerce_api.domain.vo;

public record CardVO(
        String holderName,
        String number,
        String expiryMonth,
        String expiryYear,
        String ccv
) {
}
