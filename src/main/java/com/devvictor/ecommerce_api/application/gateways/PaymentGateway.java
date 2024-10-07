package com.devvictor.ecommerce_api.application.gateways;

import com.devvictor.ecommerce_api.domain.vo.CardVO;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface PaymentGateway {
    CompletableFuture<String> createCustomer(String name, String email, String document);
    CompletableFuture<Optional<String>> findCustomerIdByDocument(String document);
    CompletableFuture<String> createCreditCardCharge(String customerId, long value, int parcels, CardVO card);
}
