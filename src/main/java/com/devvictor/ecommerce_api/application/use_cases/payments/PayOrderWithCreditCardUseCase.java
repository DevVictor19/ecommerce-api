package com.devvictor.ecommerce_api.application.use_cases.payments;

import com.devvictor.ecommerce_api.application.exceptions.NotFoundException;
import com.devvictor.ecommerce_api.application.gateways.PaymentGateway;
import com.devvictor.ecommerce_api.application.services.OrderService;
import com.devvictor.ecommerce_api.domain.entities.Order;
import com.devvictor.ecommerce_api.domain.entities.Payment;
import com.devvictor.ecommerce_api.domain.entities.User;
import com.devvictor.ecommerce_api.domain.enums.OrderStatus;
import com.devvictor.ecommerce_api.domain.factories.PaymentFactory;
import com.devvictor.ecommerce_api.domain.vo.CardVO;
import com.devvictor.ecommerce_api.domain.vo.ChargeVO;
import com.devvictor.ecommerce_api.domain.vo.CustomerVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
