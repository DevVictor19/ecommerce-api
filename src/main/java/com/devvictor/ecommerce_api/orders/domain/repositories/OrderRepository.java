package com.devvictor.ecommerce_api.orders.domain.repositories;

import com.devvictor.ecommerce_api.orders.domain.entities.Order;
import com.devvictor.ecommerce_api.orders.domain.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order, String> {
    Optional<Order> findByIdAndUserId(String id, String userId);
    Page<Order> findByUserId(String userId, Pageable pageable);
    Page<Order> findByUserIdAndStatus(String userId, OrderStatus status, Pageable pageable);
    Page<Order> findByStatus(OrderStatus status, Pageable pageable);
}
