package com.devvictor.ecommerce_api.infra.gateways.asaas;

import com.devvictor.ecommerce_api.application.gateways.PaymentGateway;
import com.devvictor.ecommerce_api.application.providers.EnvConfigProvider;
import com.devvictor.ecommerce_api.domain.vo.CardVO;
import com.devvictor.ecommerce_api.infra.gateways.asaas.dtos.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

// asaas payment gateway:
// https://docs.asaas.com/reference/about-this-doc

@Component
public class AsaasPaymentGatewayImpl implements PaymentGateway {
    private final WebClient webClient;

    public AsaasPaymentGatewayImpl(EnvConfigProvider envConfigProvider) {
        webClient = WebClient.builder()
                .baseUrl(envConfigProvider.getPaymentGatewayUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.USER_AGENT, "devvictor-ecommerce-api-spring") // Define o User-Agent
                .defaultHeader("access_token", envConfigProvider.getPaymentGatewayKey())
                .clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create().responseTimeout(Duration.ofSeconds(10))
                ))
                .build();
    }

    @Override
    public CompletableFuture<String> createCustomer(String name, String email, String document) {
        CreateCustomerDTO requestDTO = new CreateCustomerDTO(name, email, document);

        return webClient.post()
                .uri("/customers")
                .bodyValue(requestDTO)
                .retrieve()
                .bodyToMono(CustomerDTO.class)
                .toFuture()
                .thenApply(CustomerDTO::id)
                .exceptionally(ex -> { throw new RuntimeException("Failed to create customer", ex); });
    }

    @Override
    public CompletableFuture<Optional<String>> findCustomerIdByDocument(String document) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/customers")
                        .queryParam("document", document)
                        .build())
                .retrieve()
                .bodyToMono(CustomerListDTO.class)
                .toFuture()
                .thenApply(dto -> dto.data().stream().findFirst().map(CustomerDTO::id))
                .exceptionally(ex -> { throw new RuntimeException("Failed to find customers", ex); });
    }

    @Override
    public CompletableFuture<String> createCreditCardCharge(String customerId, long value, int parcels, CardVO card) {
        double totalPrice = (double) value / 100;
        LocalDate dueDate = LocalDate.now().plusMonths(1);

        CreateCreditCardChargeDTO requestDTO = new CreateCreditCardChargeDTO(
                customerId,
                "CREDIT_CARD",
                totalPrice,
                dueDate,
                parcels,
                new CardDTO(card.holderName(),
                        card.number(),
                        card.expiryMonth(),
                        card.expiryYear(),
                        card.cvv()
                )
        );

        return webClient.post()
                .uri("/payments")
                .bodyValue(requestDTO)
                .retrieve()
                .bodyToMono(ChargeDTO.class)
                .toFuture()
                .thenApply(ChargeDTO::id)
                .exceptionally(ex -> { throw new RuntimeException("Failed to create a credit card charge", ex); });
    }
}
