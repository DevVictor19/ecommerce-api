package com.devvictor.ecommerce_api.application.use_cases.orders;

import com.devvictor.ecommerce_api.application.services.OrderService;
import com.devvictor.ecommerce_api.domain.entities.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindAllUserOrdersUseCase {
    private final OrderService orderService;

    public Page<Order> execute(String userId, int page, int size) {
        return orderService.findRecentByUser(userId, page, size);
    }
}
