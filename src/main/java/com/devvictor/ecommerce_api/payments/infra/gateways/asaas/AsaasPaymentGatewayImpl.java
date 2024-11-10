package com.devvictor.ecommerce_api.payments.infra.gateways.asaas;

import com.devvictor.ecommerce_api.payments.application.gateways.PaymentGateway;
import com.devvictor.ecommerce_api.payments.infra.gateways.asaas.dtos.*;
import com.devvictor.ecommerce_api.shared.application.providers.EnvConfigProvider;
import com.devvictor.ecommerce_api.payments.domain.vo.CardVO;
import com.devvictor.ecommerce_api.payments.domain.vo.ChargeVO;
import com.devvictor.ecommerce_api.payments.domain.vo.CustomerVO;
import com.devvictor.ecommerce_api.payments.infra.mappers.PaymentGatewayMapper;
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
    private final PaymentGatewayMapper mapper;

    public AsaasPaymentGatewayImpl(EnvConfigProvider envConfigProvider, PaymentGatewayMapper mapper) {
        webClient = WebClient.builder()
                .baseUrl(envConfigProvider.getPaymentGatewayUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.USER_AGENT, "devvictor-ecommerce-api-spring") // Define o User-Agent
                .defaultHeader("access_token", envConfigProvider.getPaymentGatewayKey())
                .clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create().responseTimeout(Duration.ofSeconds(10))
                ))
                .build();

        this.mapper = mapper;
    }

    @Override
    public CompletableFuture<CustomerVO> createCustomer(String name, String email, String document) {
        CreateCustomerDTO requestDTO = new CreateCustomerDTO(name, email, document);

        return webClient.post()
                .uri("/customers")
                .bodyValue(requestDTO)
                .retrieve()
                .bodyToMono(CustomerDTO.class)
                .map(mapper::toCustomerVO)
                .toFuture();
    }

    @Override
    public CompletableFuture<Optional<CustomerVO>> findCustomerIdByDocument(String document) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/customers")
                        .queryParam("document", document)
                        .build())
                .retrieve()
                .bodyToMono(CustomerListDTO.class)
                .map((dto) -> dto.data().stream().findFirst().map(mapper::toCustomerVO))
                .toFuture();
    }

    @Override
    public CompletableFuture<ChargeVO> createCreditCardCharge(String customerId,
                                                              String remoteIp,
                                                              long value,
                                                              int parcels,
                                                              CardVO card) {

        double totalPrice = (double) value / 100;
        double installmentValue =  totalPrice / parcels;

        LocalDate dueDate = LocalDate.now().plusMonths(1);

        CreateCreditCardChargeDTO requestDTO = new CreateCreditCardChargeDTO(
                customerId,
                remoteIp,
                "CREDIT_CARD",
                totalPrice,
                dueDate.toString(),
                parcels,
                installmentValue,
                mapper.toCardDTO(card)
        );

        return webClient.post()
                .uri("/payments")
                .bodyValue(requestDTO)
                .retrieve()
                .bodyToMono(ChargeDTO.class)
                .map(mapper::toChargeVO)
                .toFuture();
    }
}
