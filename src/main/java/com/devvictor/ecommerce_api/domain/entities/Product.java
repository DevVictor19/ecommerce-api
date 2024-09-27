package com.devvictor.ecommerce_api.domain.entities;

import com.devvictor.ecommerce_api.domain.exceptions.InsufficientStockQuantityException;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class Product {
    @Id
    private String id;
    private double price;

    @TextIndexed
    private String name;
    private String description;

    @Field(name = "photo_url")
    private String photoUrl;

    @Field(name = "stock_qnt")
    private int stockQuantity;

    @Field(name = "created_at", targetType = FieldType.DATE_TIME)
    private Date createdAt;

    public void subtractFromStock(int quantity) {
        if (quantity > stockQuantity) {
            throw new InsufficientStockQuantityException();
        }

        stockQuantity -= quantity;
    }
}
