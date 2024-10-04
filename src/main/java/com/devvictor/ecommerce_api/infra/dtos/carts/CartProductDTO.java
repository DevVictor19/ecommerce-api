package com.devvictor.ecommerce_api.infra.dtos.carts;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.UUID;

public record CartProductDTO(
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

        @Positive
        int inCartQuantity
) {
}
