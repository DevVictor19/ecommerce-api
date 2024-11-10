package com.devvictor.ecommerce_api.orders.application.usecases;

import com.devvictor.ecommerce_api.orders.application.services.OrderService;
import com.devvictor.ecommerce_api.orders.domain.entities.Order;
import com.devvictor.ecommerce_api.orders.domain.enums.OrderStatus;
import com.devvictor.ecommerce_api.shared.application.exceptions.BadRequestException;
import com.devvictor.ecommerce_api.shared.application.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CancelOrderUseCase {
    private final OrderService orderService;

    public void execute(String orderId, String userId) {
       Order order = orderService.findByIdAndUserId(orderId, userId)
               .orElseThrow(() -> new NotFoundException("Order not found"));

       if (order.getStatus() != OrderStatus.WAITING_PAYMENT) {
           throw new BadRequestException("This order cant be canceled");
       }

       orderService.delete(order);
    }
}
