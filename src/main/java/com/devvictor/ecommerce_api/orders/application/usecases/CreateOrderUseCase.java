package com.devvictor.ecommerce_api.orders.application.usecases;

import com.devvictor.ecommerce_api.cart.application.services.CartService;
import com.devvictor.ecommerce_api.cart.domain.entities.Cart;
import com.devvictor.ecommerce_api.orders.application.services.OrderService;
import com.devvictor.ecommerce_api.orders.domain.entities.Order;
import com.devvictor.ecommerce_api.orders.domain.entities.OrderCart;
import com.devvictor.ecommerce_api.orders.domain.factories.OrderCartFactory;
import com.devvictor.ecommerce_api.orders.domain.factories.OrderFactory;
import com.devvictor.ecommerce_api.products.application.services.ProductService;
import com.devvictor.ecommerce_api.shared.application.exceptions.BadRequestException;
import com.devvictor.ecommerce_api.shared.application.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletionException;

@Component
@RequiredArgsConstructor
public class CreateOrderUseCase {
    private final CartService cartService;
    private final OrderService orderService;
    private final ProductService productService;

    public Order execute(String userId) {
        Cart cart = cartService.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Cart not found"));

        try {
            productService.subtractProductsFromStock(cart.getProducts()).join();
        } catch (CompletionException e) {
            Throwable throwable = e.getCause();
            throw new BadRequestException("Products not available", throwable);
        }

        OrderCart orderCart = OrderCartFactory.create(
                cart.getId(),
                cart.getProducts(),
                cart.getProductsQuantity(),
                cart.getTotalPrice(),
                cart.getCreatedAt()
        );

        Order order = OrderFactory.create(userId, orderCart);
        orderService.create(order);
        cartService.delete(cart);

        return order;
    }
}
