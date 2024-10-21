package com.devvictor.ecommerce_api.orders.application.services;

import com.devvictor.ecommerce_api.orders.domain.entities.Order;
import com.devvictor.ecommerce_api.orders.domain.enums.OrderStatus;
import com.devvictor.ecommerce_api.orders.domain.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<Order> findAllUserOrders(int page,
                                         int size,
                                         String sort,
                                         String sortBy,
                                         String userId,
                                         OrderStatus status) {

        Sort.Direction direction = sort.equalsIgnoreCase("asc")
                ? Sort.Direction.ASC
                : Sort.Direction.DESC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        if (status != null) {
            return orderRepository.findByUserIdAndStatus(userId, status, pageable);
        }

        return orderRepository.findByUserId(userId, pageable);
    }

    public Page<Order> findAllOrders(int page,
                                     int size,
                                     String sort,
                                     String sortBy,
                                     OrderStatus status) {

        Sort.Direction direction = sort.equalsIgnoreCase("asc")
                ? Sort.Direction.ASC
                : Sort.Direction.DESC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        if (status != null) {
            return orderRepository.findByStatus(status, pageable);
        }

        return orderRepository.findAll(pageable);
    }

    public void delete(Order order) {
        orderRepository.delete(order);
    }

    public void update(Order order) {
        orderRepository.save(order);
    }
}
