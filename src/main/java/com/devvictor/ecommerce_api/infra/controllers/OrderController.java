package com.devvictor.ecommerce_api.infra.controllers;

import com.devvictor.ecommerce_api.application.dtos.input.CancelOrderInputDTO;
import com.devvictor.ecommerce_api.application.dtos.input.CreateOrderInputDTO;
import com.devvictor.ecommerce_api.application.exceptions.InternalServerErrorException;
import com.devvictor.ecommerce_api.application.use_cases.orders.CancelOrderUseCase;
import com.devvictor.ecommerce_api.application.use_cases.orders.CreateOrderUseCase;
import com.devvictor.ecommerce_api.domain.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final CreateOrderUseCase createOrderUseCase;
    private final CancelOrderUseCase cancelOrderUseCase;

    @PostMapping("/my-orders")
    public ResponseEntity<Void> createOrder() {
        var user = (Optional<User>) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (user.isEmpty() ) {
            throw new InternalServerErrorException();
        }

        var input = new CreateOrderInputDTO(user.get().getId());

        createOrderUseCase.execute(input);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/my-orders/{orderId}")
    public ResponseEntity<Void> cancelOrder(@PathVariable UUID orderId) {
        var user = (Optional<User>) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (user.isEmpty() ) {
            throw new InternalServerErrorException();
        }

        var input = new CancelOrderInputDTO(orderId.toString(), user.get().getId());

        cancelOrderUseCase.execute(input);

        return ResponseEntity.ok().build();
    }
}