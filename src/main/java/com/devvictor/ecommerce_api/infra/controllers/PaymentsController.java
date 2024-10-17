package com.devvictor.ecommerce_api.infra.controllers;

import com.devvictor.ecommerce_api.application.exceptions.InternalServerErrorException;
import com.devvictor.ecommerce_api.application.use_cases.payments.PayOrderWithCreditCardUseCase;
import com.devvictor.ecommerce_api.domain.entities.User;
import com.devvictor.ecommerce_api.infra.dtos.payments.CreateCreditCardChargeDTO;
import com.devvictor.ecommerce_api.infra.mappers.PaymentEntityMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentsController {
    private final PayOrderWithCreditCardUseCase payOrderWithCreditCardUseCase;
    private final PaymentEntityMapper paymentEntityMapper;

    @PostMapping("/credit/orders/{orderId}")
    public ResponseEntity<Void> payOrderWithCreditCard(HttpServletRequest request,
                                                       @RequestBody @Valid CreateCreditCardChargeDTO dto,
                                                       @PathVariable UUID orderId) {
        payOrderWithCreditCardUseCase.execute(
                orderId.toString(),
                request.getRemoteAddr(),
                dto.document(),
                dto.parcels(),
                paymentEntityMapper.toCardVO(dto.card()),
                getAuthenticatedUser()
        );

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    private User getAuthenticatedUser() {
        Optional<User> user = (Optional<User>) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (user.isEmpty() ) {
            throw new InternalServerErrorException();
        }

        return user.get();
    }
}
