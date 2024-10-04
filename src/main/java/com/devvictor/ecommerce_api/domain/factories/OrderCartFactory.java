package com.devvictor.ecommerce_api.domain.factories;

import com.devvictor.ecommerce_api.domain.entities.CartProduct;
import com.devvictor.ecommerce_api.domain.entities.OrderCart;

import java.util.Date;
import java.util.List;

public class OrderCartFactory {
    public static OrderCart create(String id,
                                   List<CartProduct> products,
                                   int productsQuantity,
                                   long totalPrice,
                                   Date createdAt) {

        var entity = new OrderCart();
        entity.setId(id);
        entity.setProducts(products);
        entity.setProductsQuantity(productsQuantity);
        entity.setTotalPrice(totalPrice);
        entity.setCreatedAt(createdAt);
        return entity;
    }
}
