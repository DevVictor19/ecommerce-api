package com.devvictor.ecommerce_api.domain.entities;

import com.devvictor.ecommerce_api.domain.enums.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private String userId;
    private OrderStatus status;
    private Cart cart;
    private Payment payment;

    @Field(name = "created_at", targetType = FieldType.DATE_TIME)
    private Date createdAt;
}
