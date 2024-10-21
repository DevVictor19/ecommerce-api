package com.devvictor.ecommerce_api.products.domain.factories;

import com.devvictor.ecommerce_api.products.domain.entities.Product;

import java.util.Date;
import java.util.UUID;

public class ProductFactory {
    public static Product create(long price,
                                 String name,
                                 String description,
                                 String photoUrl,
                                 int stockQuantity) {

        var entity = new Product();
        entity.setId(UUID.randomUUID().toString());
        entity.setPrice(price);
        entity.setName(name);
        entity.setDescription(description);
        entity.setPhotoUrl(photoUrl);
        entity.setStockQuantity(stockQuantity);
        entity.setCreatedAt(new Date());
        return entity;
    }
}
