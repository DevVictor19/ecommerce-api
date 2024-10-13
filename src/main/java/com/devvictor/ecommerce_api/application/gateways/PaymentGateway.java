package com.devvictor.ecommerce_api.application.gateways;

import com.devvictor.ecommerce_api.domain.vo.CardVO;
import com.devvictor.ecommerce_api.domain.vo.ChargeVO;
import com.devvictor.ecommerce_api.domain.vo.CustomerVO;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface PaymentGateway {
    CompletableFuture<CustomerVO> createCustomer(String name, String email, String document);
    CompletableFuture<Optional<CustomerVO>> findCustomerIdByDocument(String document);
    CompletableFuture<ChargeVO> createCreditCardCharge(String customerId, long value, int parcels, CardVO card);
}
