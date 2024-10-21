package com.devvictor.ecommerce_api.orders.application.usecases;

import com.devvictor.ecommerce_api.shared.application.exceptions.NotFoundException;
import com.devvictor.ecommerce_api.cart.application.services.CartService;
import com.devvictor.ecommerce_api.orders.application.services.OrderService;
import com.devvictor.ecommerce_api.cart.domain.entities.Cart;
import com.devvictor.ecommerce_api.orders.domain.entities.Order;
import com.devvictor.ecommerce_api.orders.domain.entities.OrderCart;
import com.devvictor.ecommerce_api.orders.domain.factories.OrderCartFactory;
import com.devvictor.ecommerce_api.orders.domain.factories.OrderFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CreateOrderUseCase {
    private final CartService cartService;
    private final OrderService orderService;

    public void execute(String userId) {
        Optional<Cart> cart = cartService.findByUserId(userId);

        if (cart.isEmpty()) {
            throw new NotFoundException("Cart not found");
        }

        OrderCart orderCart = OrderCartFactory.create(
                cart.get().getId(),
                cart.get().getProducts(),
                cart.get().getProductsQuantity(),
                cart.get().getTotalPrice(),
                cart.get().getCreatedAt()
        );

        Order order = OrderFactory.create(userId, orderCart);

        orderService.create(order);

        cartService.delete(cart.get());
    }
}
