package com.devvictor.ecommerce_api.domain.factories;

import com.devvictor.ecommerce_api.domain.entities.CartProduct;

import java.util.Date;

public class CartProductFactory {

    public static CartProduct create(String id,
                                     long price,
                                     String name,
                                     String description,
                                     String photoUrl,
                                     int inCartQuantity) {

        var entity = new CartProduct();
        entity.setId(id);
        entity.setPrice(price);
        entity.setName(name);
        entity.setDescription(description);
        entity.setPhotoUrl(photoUrl);
        entity.setInCartQuantity(inCartQuantity);
        return entity;
    }
}
