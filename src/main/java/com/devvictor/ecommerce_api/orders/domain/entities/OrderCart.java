package com.devvictor.ecommerce_api.orders.domain.entities;

import com.devvictor.ecommerce_api.cart.domain.entities.CartProduct;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderCart {
    @Id
    private String id;

    private List<CartProduct> products = new ArrayList<>();

    @Field(name = "products_qnt")
    private int productsQuantity;

    @Field(name = "total_price")
    private long totalPrice; // cents

    @Field(name = "created_at", targetType = FieldType.DATE_TIME)
    private Date createdAt;
}
