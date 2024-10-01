package com.devvictor.ecommerce_api.application.use_cases.orders;

import com.devvictor.ecommerce_api.application.dtos.orders.CancelOrderInputDTO;
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

    public void execute(CancelOrderInputDTO dto) {
        Optional<Order> order = orderService.findByIdAndUserId(dto.orderId(), dto.userId());

        if (order.isEmpty()) {
            throw new NotFoundException("Order not found");
        }

        orderService.delete(order.get());
    }
}
