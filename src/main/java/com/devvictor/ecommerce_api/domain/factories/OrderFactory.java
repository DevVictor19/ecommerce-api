package com.devvictor.ecommerce_api.domain.factories;

import com.devvictor.ecommerce_api.domain.entities.Cart;
import com.devvictor.ecommerce_api.domain.entities.Order;
import com.devvictor.ecommerce_api.domain.enums.OrderStatus;

import java.util.Date;
import java.util.UUID;

public class OrderFactory {
    public static Order create(String userId, Cart cart) {
        var entity = new Order();
        entity.setId(UUID.randomUUID().toString());
        entity.setUserId(userId);
        entity.setStatus(OrderStatus.WAITING_PAYMENT);
        entity.setCart(cart);
        entity.setCreatedAt(new Date());
        return entity;
    }

    public static Order create(String userId, Cart cart, OrderStatus status) {
        var entity = new Order();
        entity.setId(UUID.randomUUID().toString());
        entity.setUserId(userId);
        entity.setStatus(status);
        entity.setCart(cart);
        entity.setCreatedAt(new Date());
        return entity;
    }
}
