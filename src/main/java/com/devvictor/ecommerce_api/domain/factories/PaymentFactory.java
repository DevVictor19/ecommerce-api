package com.devvictor.ecommerce_api.domain.factories;

import com.devvictor.ecommerce_api.domain.entities.Payment;
import com.devvictor.ecommerce_api.domain.enums.PaymentMethod;

import java.util.Date;
import java.util.UUID;

public class PaymentFactory {
    public static Payment create(String transactionCode, long price, int parcels) {
        var entity = new Payment();
        entity.setId(UUID.randomUUID().toString());
        entity.setTransactionCode(transactionCode);
        entity.setPrice(price);
        entity.setMethod(PaymentMethod.CREDIT_CARD);
        entity.setParcels(parcels);
        entity.setCreatedAt(new Date());
        return entity;
    }

    public static Payment create(String transactionCode, long price) {
        var entity = new Payment();
        entity.setId(UUID.randomUUID().toString());
        entity.setTransactionCode(transactionCode);
        entity.setPrice(price);
        entity.setMethod(PaymentMethod.DEBIT_CARD);
        entity.setParcels(0);
        entity.setCreatedAt(new Date());
        return entity;
    }
}
