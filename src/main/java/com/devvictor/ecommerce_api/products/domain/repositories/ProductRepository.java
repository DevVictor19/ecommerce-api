package com.devvictor.ecommerce_api.products.domain.repositories;

import com.devvictor.ecommerce_api.products.domain.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findByName(String name);
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
