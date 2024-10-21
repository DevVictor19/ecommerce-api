package com.devvictor.ecommerce_api.orders.application.usecases;

import com.devvictor.ecommerce_api.orders.application.services.OrderService;
import com.devvictor.ecommerce_api.orders.domain.entities.Order;
import com.devvictor.ecommerce_api.orders.domain.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindAllUserOrdersUseCase {
    private final OrderService orderService;

    public Page<Order> execute(int page,
                               int size,
                               String sort,
                               String sortBy,
                               String userId,
                               OrderStatus status) {

        return orderService.findAllUserOrders(page, size, sort, sortBy, userId, status);
    }
}
