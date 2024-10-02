package com.devvictor.ecommerce_api.application.use_cases.orders;

import com.devvictor.ecommerce_api.application.exceptions.NotFoundException;
import com.devvictor.ecommerce_api.application.services.OrderService;
import com.devvictor.ecommerce_api.domain.entities.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CancelOrderUseCase {
    private final OrderService orderService;

    public void execute(String orderId, String userId) {
        Optional<Order> order = orderService.findByIdAndUserId(orderId, userId);

        if (order.isEmpty()) {
            throw new NotFoundException("Order not found");
        }

        orderService.delete(order.get());
    }
}
