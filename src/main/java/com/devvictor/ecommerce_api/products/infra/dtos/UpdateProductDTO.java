package com.devvictor.ecommerce_api.products.infra.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

public record UpdateProductDTO(
        @Positive
        long price,

        @NotBlank
        @Length(min = 4, max = 25)
        String name,

        @NotBlank
        @Length(min = 10, max = 100)
        String description,

        @URL
        String photoUrl,

        @PositiveOrZero
        int stockQuantity
) {
}
