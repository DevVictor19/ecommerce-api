package com.devvictor.ecommerce_api.payments.application.usecases;

import com.devvictor.ecommerce_api.orders.application.services.OrderService;
import com.devvictor.ecommerce_api.orders.domain.entities.Order;
import com.devvictor.ecommerce_api.orders.domain.enums.OrderStatus;
import com.devvictor.ecommerce_api.payments.application.gateways.PaymentGateway;
import com.devvictor.ecommerce_api.payments.domain.entities.Payment;
import com.devvictor.ecommerce_api.payments.domain.factories.PaymentFactory;
import com.devvictor.ecommerce_api.payments.domain.vo.CardVO;
import com.devvictor.ecommerce_api.payments.domain.vo.ChargeVO;
import com.devvictor.ecommerce_api.payments.domain.vo.CustomerVO;
import com.devvictor.ecommerce_api.shared.application.exceptions.BadRequestException;
import com.devvictor.ecommerce_api.shared.application.exceptions.NotFoundException;
import com.devvictor.ecommerce_api.user.domain.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class PayOrderWithCreditCardUseCase {
    private final PaymentGateway paymentGateway;
    private final OrderService orderService;

    public void execute(String orderId,
                        String remoteIp,
                        String document,
                        int parcels,
                        CardVO card,
                        User user) {

        Order order = orderService.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found"));

        if (!Objects.equals(order.getUserId(), user.getId())) {
            throw new BadRequestException("User don't owns this order");
        }

        if (order.getPayment() != null) {
            throw new BadRequestException("This order already have a payment");
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
