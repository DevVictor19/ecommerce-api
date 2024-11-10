package com.devvictor.ecommerce_api.orders.application.usecases;

import com.devvictor.ecommerce_api.orders.application.services.OrderService;
import com.devvictor.ecommerce_api.orders.domain.entities.Order;
import com.devvictor.ecommerce_api.orders.domain.enums.OrderStatus;
import com.devvictor.ecommerce_api.products.application.services.ProductService;
import com.devvictor.ecommerce_api.shared.application.exceptions.BadRequestException;
import com.devvictor.ecommerce_api.shared.application.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletionException;

@Component
@RequiredArgsConstructor
public class CancelOrderUseCase {
    private final OrderService orderService;
    private final ProductService productService;

    public void execute(String orderId, String userId) {
       Order order = orderService.findByIdAndUserId(orderId, userId)
               .orElseThrow(() -> new NotFoundException("Order not found"));


       if (order.getStatus() != OrderStatus.WAITING_PAYMENT) {
           throw new BadRequestException("This order cant be canceled");
       }

       try {
           productService.addProductsToStock(order.getCart().getProducts()).join();
       } catch (CompletionException e) {
           Throwable cause = e.getCause();
           throw new NotFoundException("Products not found", cause);
       }

       orderService.delete(order);
    }
}
