package com.devvictor.ecommerce_api.application.services;

import com.devvictor.ecommerce_api.domain.entities.Order;
import com.devvictor.ecommerce_api.domain.enums.OrderStatus;
import com.devvictor.ecommerce_api.domain.repositories.OrderRepository;
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

    public Page<Order> findRecentByUser(String userId, int page, int size) {
        Sort sort = Sort.by("created_at").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
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
        } else {
            return orderRepository.findAll(pageable);
        }
    }

    public void delete(Order order) {
        orderRepository.delete(order);
    }
}
