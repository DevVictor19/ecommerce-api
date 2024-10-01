package com.devvictor.ecommerce_api.application.use_cases.orders;

import com.devvictor.ecommerce_api.application.dtos.input.CreateOrderInputDTO;
import com.devvictor.ecommerce_api.application.exceptions.NotFoundException;
import com.devvictor.ecommerce_api.application.services.CartService;
import com.devvictor.ecommerce_api.application.services.OrderService;
import com.devvictor.ecommerce_api.domain.entities.Cart;
import com.devvictor.ecommerce_api.domain.entities.Order;
import com.devvictor.ecommerce_api.domain.factories.OrderFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CreateOrderUseCase {
    private final CartService cartService;
    private final OrderService orderService;

    public void execute(CreateOrderInputDTO dto) {
        Optional<Cart> cart = cartService.findByUserId(dto.userId());

        if (cart.isEmpty()) {
            throw new NotFoundException("Cart not found");
        }

        Order order = OrderFactory.create(dto.userId(), cart.get());

        orderService.create(order);

        cartService.delete(cart.get());
    }
}
