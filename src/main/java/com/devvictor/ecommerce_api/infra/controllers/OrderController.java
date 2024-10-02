package com.devvictor.ecommerce_api.infra.controllers;

import com.devvictor.ecommerce_api.application.exceptions.InternalServerErrorException;
import com.devvictor.ecommerce_api.application.use_cases.orders.CancelOrderUseCase;
import com.devvictor.ecommerce_api.application.use_cases.orders.CreateOrderUseCase;
import com.devvictor.ecommerce_api.application.use_cases.orders.FindAllUserOrdersUseCase;
import com.devvictor.ecommerce_api.domain.entities.User;
import com.devvictor.ecommerce_api.infra.dtos.orders.OrderDTO;
import com.devvictor.ecommerce_api.infra.mappers.OrderEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    private final FindAllUserOrdersUseCase findAllUserOrdersUseCase;
    private final CreateOrderUseCase createOrderUseCase;
    private final CancelOrderUseCase cancelOrderUseCase;
    private final OrderEntityMapper orderEntityMapper;

    @GetMapping("/my-orders")
    public ResponseEntity<Page<OrderDTO>> findAllUserOrders(@RequestParam int page,
                                                            @RequestParam int size) {

        return ResponseEntity.ok(findAllUserOrdersUseCase
                .execute(getAuthenticatedUser().getId(), page, size)
                .map(orderEntityMapper::toDto)
        );
    }

    @PostMapping("/my-orders")
    public ResponseEntity<Void> createOrder() {
        createOrderUseCase.execute(getAuthenticatedUser().getId());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/my-orders/{orderId}")
    public ResponseEntity<Void> cancelOrder(@PathVariable UUID orderId) {
        cancelOrderUseCase.execute(orderId.toString(), getAuthenticatedUser().getId());

        return ResponseEntity.ok().build();
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
