package com.devvictor.ecommerce_api.domain.repositories;

import com.devvictor.ecommerce_api.domain.entities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findByName(String name);
}
