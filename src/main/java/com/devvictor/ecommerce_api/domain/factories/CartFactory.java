package com.devvictor.ecommerce_api.domain.factories;

import com.devvictor.ecommerce_api.domain.entities.Cart;

import java.util.Date;
import java.util.UUID;

public class CartFactory {
    public static Cart create(String userId) {
        var entity = new Cart();
        entity.setId(UUID.randomUUID().toString());
        entity.setUserId(userId);
        entity.setCreatedAt(new Date());
        return entity;
    }
}
