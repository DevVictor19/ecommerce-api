package com.devvictor.ecommerce_api.orders.infra.controllers;

import com.devvictor.ecommerce_api.orders.application.usecases.CancelOrderUseCase;
import com.devvictor.ecommerce_api.orders.application.usecases.CreateOrderUseCase;
import com.devvictor.ecommerce_api.orders.application.usecases.FindAllOrdersUseCase;
import com.devvictor.ecommerce_api.orders.application.usecases.FindAllUserOrdersUseCase;
import com.devvictor.ecommerce_api.orders.domain.enums.OrderStatus;
import com.devvictor.ecommerce_api.orders.infra.dtos.OrderDTO;
import com.devvictor.ecommerce_api.orders.infra.mappers.OrderEntityMapper;
import com.devvictor.ecommerce_api.shared.application.exceptions.InternalServerErrorException;
import com.devvictor.ecommerce_api.user.domain.entities.User;
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
    public ResponseEntity<OrderDTO> createOrder() {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderEntityMapper.toDto(createOrderUseCase.execute(getAuthenticatedUser().getId())));
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
