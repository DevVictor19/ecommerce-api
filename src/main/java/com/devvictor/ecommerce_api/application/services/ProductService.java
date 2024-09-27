package com.devvictor.ecommerce_api.application.services;

import com.devvictor.ecommerce_api.domain.entities.Product;
import com.devvictor.ecommerce_api.domain.factories.ProductFactory;
import com.devvictor.ecommerce_api.domain.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public void create( double price,
                        String name,
                        String description,
                        String photoUrl,
                        int stockQuantity) {

        Product product = ProductFactory.create(
                price,
                name,
                description,
                photoUrl,
                stockQuantity);

        productRepository.insert(product);
    }

    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    public Optional<Product> findById(String id) {
        return productRepository.findById(id);
    }

    public void delete(Product entity) {
        productRepository.delete(entity);
    }
}
