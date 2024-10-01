package com.devvictor.ecommerce_api.application.services;

import com.devvictor.ecommerce_api.domain.entities.Order;
import com.devvictor.ecommerce_api.domain.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public void create(Order order) {
        orderRepository.insert(order);
    }

    public Optional<Order> findById(String id) {
        return orderRepository.findById(id);
    }

    public Optional<Order> findByIdAndUserId(String orderId, String userId) {
        return orderRepository.findByIdAndUserId(orderId, userId);
    }

    public void delete(Order order) {
        orderRepository.delete(order);
    }
}
