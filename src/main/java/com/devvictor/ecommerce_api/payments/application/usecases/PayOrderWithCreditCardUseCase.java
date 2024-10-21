package com.devvictor.ecommerce_api.payments.application.usecases;

import com.devvictor.ecommerce_api.shared.application.exceptions.BadRequestException;
import com.devvictor.ecommerce_api.shared.application.exceptions.NotFoundException;
import com.devvictor.ecommerce_api.payments.application.gateways.PaymentGateway;
import com.devvictor.ecommerce_api.orders.application.services.OrderService;
import com.devvictor.ecommerce_api.products.application.services.ProductService;
import com.devvictor.ecommerce_api.orders.domain.entities.Order;
import com.devvictor.ecommerce_api.payments.domain.entities.Payment;
import com.devvictor.ecommerce_api.user.domain.entities.User;
import com.devvictor.ecommerce_api.orders.domain.enums.OrderStatus;
import com.devvictor.ecommerce_api.payments.domain.factories.PaymentFactory;
import com.devvictor.ecommerce_api.shared.domain.vo.CardVO;
import com.devvictor.ecommerce_api.shared.domain.vo.ChargeVO;
import com.devvictor.ecommerce_api.shared.domain.vo.CustomerVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PayOrderWithCreditCardUseCase {
    private final PaymentGateway paymentGateway;
    private final OrderService orderService;
    private final ProductService productService;

    public void execute(String orderId,
                        String remoteIp,
                        String document,
                        int parcels,
                        CardVO card,
                        User user) {

        Order order = orderService.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found"));

        if (order.getPayment() != null) {
            throw new BadRequestException("This order already have a payment");
        }

        boolean isProductsAvailable = productService
                .isProductsAvailable(order.getCart().getProducts());

        if (!isProductsAvailable) {
            throw new BadRequestException("Products not available");
        }

        CustomerVO customer = paymentGateway.findCustomerIdByDocument(document).join()
                .orElseGet(() -> paymentGateway.createCustomer(
                        user.getUsername(),
                        user.getEmail(),
                        document).join()
                );

        ChargeVO charge = paymentGateway.createCreditCardCharge(
                customer.id(),
                remoteIp,
                order.getCart().getTotalPrice(),
                parcels,
                card
        ).join();

        Payment payment = PaymentFactory.create(
                charge.id(),
                order.getCart().getTotalPrice(),
                parcels
        );

        order.setPayment(payment);
        order.setStatus(OrderStatus.PAID);

        orderService.update(order);
    }
}
