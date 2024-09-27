package com.devvictor.ecommerce_api.infra.contracts.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

public record UpdateProductRequest(
        @Digits(integer = 10, fraction = 2)
        @Positive
        Double price,

        @NotBlank
        @Length(min = 4, max = 25)
        String name,

        @NotBlank
        @Length(min = 10, max = 100)
        String description,

        @URL
        String photoUrl,

        @PositiveOrZero
        Integer stockQuantity
) {
}
