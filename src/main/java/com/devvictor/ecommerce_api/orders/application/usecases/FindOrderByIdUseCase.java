package com.devvictor.ecommerce_api.orders.application.usecases;

import com.devvictor.ecommerce_api.orders.application.services.OrderService;
import com.devvictor.ecommerce_api.orders.domain.entities.Order;
import com.devvictor.ecommerce_api.shared.application.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindOrderByIdUseCase {
    private final OrderService orderService;

    public Order execute(String orderId) {
        return orderService.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found"));
    }
}
