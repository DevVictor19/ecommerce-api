package com.devvictor.ecommerce_api.infra.controllers;

import com.devvictor.ecommerce_api.application.exceptions.InternalServerErrorException;
import com.devvictor.ecommerce_api.application.use_cases.orders.CancelOrderUseCase;
import com.devvictor.ecommerce_api.application.use_cases.orders.CreateOrderUseCase;
import com.devvictor.ecommerce_api.application.use_cases.orders.FindAllOrdersUseCase;
import com.devvictor.ecommerce_api.application.use_cases.orders.FindAllUserOrdersUseCase;
import com.devvictor.ecommerce_api.domain.entities.User;
import com.devvictor.ecommerce_api.domain.enums.OrderStatus;
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
    private final FindAllOrdersUseCase findAllOrdersUseCase;
    private final FindAllUserOrdersUseCase findAllUserOrdersUseCase;
    private final CreateOrderUseCase createOrderUseCase;
    private final CancelOrderUseCase cancelOrderUseCase;
    private final OrderEntityMapper orderEntityMapper;

    @GetMapping
    public ResponseEntity<Page<OrderDTO>> findAllOrders(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size,
                                                        @RequestParam(defaultValue = "desc") String sort,
                                                        @RequestParam(defaultValue = "createdAt") String sortBy,
                                                        @RequestParam(required = false) OrderStatus status) {

        return ResponseEntity.ok(findAllOrdersUseCase
                .execute(page, size, sort, sortBy, status)
                .map(orderEntityMapper::toDto)
        );
    }

    @GetMapping("/my-orders")
    public ResponseEntity<Page<OrderDTO>> findAllUserOrders(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size,
                                                            @RequestParam(defaultValue = "desc") String sort,
                                                            @RequestParam(defaultValue = "createdAt") String sortBy,
                                                            @RequestParam(required = false) OrderStatus status) {

        return ResponseEntity.ok(findAllUserOrdersUseCase
                .execute(page, size, sort, sortBy, getAuthenticatedUser().getId(), status)
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
