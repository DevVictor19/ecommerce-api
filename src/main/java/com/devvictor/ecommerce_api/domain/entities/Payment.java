package com.devvictor.ecommerce_api.domain.entities;

import com.devvictor.ecommerce_api.domain.enums.PaymentMethod;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Payment {
    @Id
    private String id;

    @Field(name = "transaction_code")
    private String transactionCode;

    private long price;
    private PaymentMethod method;
    private int parcels;

    @Field(name = "created_at", targetType = FieldType.DATE_TIME)
    private Date createdAt;
}
