package com.devvictor.ecommerce_api.domain.factories;

import com.devvictor.ecommerce_api.domain.entities.Product;

import java.util.Date;
import java.util.UUID;

public class ProductFactory {
    public static Product create(double price,
                                 String name,
                                 String description,
                                 String photoUrl,
                                 int stockQuantity) {

        return Product.builder()
                .id(UUID.randomUUID().toString())
                .price(price)
                .name(name)
                .description(description)
                .photoUrl(photoUrl)
                .stockQuantity(stockQuantity)
                .createdAt(new Date())
                .build();
    }
}
