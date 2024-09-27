package com.devvictor.ecommerce_api.application.dtos.input;

public record CreateProductInputDTO(
        Double price,
        String name,
        String description,
        String photoUrl,
        Integer stockQuantity
) {
}
