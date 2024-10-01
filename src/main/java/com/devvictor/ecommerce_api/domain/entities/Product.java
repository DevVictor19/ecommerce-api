package com.devvictor.ecommerce_api.domain.entities;

import com.devvictor.ecommerce_api.domain.exceptions.InvalidEntityOperationException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "products")
public class Product {
    @Id
    private String id;
    private long price; // cents

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
            throw new InvalidEntityOperationException("Quantity to subtract no available on stock");
        }

        stockQuantity -= quantity;
    }
}
