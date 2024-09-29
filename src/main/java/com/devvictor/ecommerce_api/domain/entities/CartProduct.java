package com.devvictor.ecommerce_api.domain.entities;

import com.devvictor.ecommerce_api.domain.exceptions.InvalidEntityOperationException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
public class CartProduct {
    private String id;
    private double price;
    private String name;
    private String description;

    @Field(name = "photo_url")
    private String photoUrl;

    @Field(name = "in_cart_qnt")
    private int inCartQuantity;

    public void addInCartQuantity(int quantityToAdd) {
        if (quantityToAdd <= 0) {
            throw new InvalidEntityOperationException("Quantity to add must be a positive value");
        }

        inCartQuantity += quantityToAdd;
    }

    public void subtractInCartQuantity(int quantityToSub) {
        if (quantityToSub >= 0) {
            throw new InvalidEntityOperationException("Quantity to subtract must be a negative value");
        }

        if (inCartQuantity - quantityToSub < 0) {
            throw new InvalidEntityOperationException("In cart quantity cannot be less than zero");
        }

        inCartQuantity -= quantityToSub;
    }
}