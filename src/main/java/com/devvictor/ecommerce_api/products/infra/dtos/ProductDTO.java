package com.devvictor.ecommerce_api.products.infra.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.UUID;

import java.util.Date;

public record ProductDTO(
        @UUID
        String id,

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
        int stockQuantity,

        @NotNull
        Date createdAt
) {
}
