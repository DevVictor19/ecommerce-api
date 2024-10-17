package com.devvictor.ecommerce_api.application.services;

import com.devvictor.ecommerce_api.domain.entities.CartProduct;
import com.devvictor.ecommerce_api.domain.entities.Product;
import com.devvictor.ecommerce_api.domain.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Page<Product> findAll(int page,
                                 int size,
                                 String sort,
                                 String sortBy,
                                 String name) {

        Sort.Direction direction = sort.equalsIgnoreCase("asc")
                ? Sort.Direction.ASC
                : Sort.Direction.DESC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        if (name == null || name.trim().isEmpty()) {
            return productRepository.findAll(pageable);
        } else {
            return productRepository.findByNameContainingIgnoreCase(name, pageable);
        }
    }

    public void create(Product product) {
        productRepository.insert(product);
    }

    public void update(Product entity) {
        productRepository.save(entity);
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

    public boolean isProductsAvailable(List<CartProduct> products) {
        List<CompletableFuture<Boolean>> futures = products
                .stream()
                .map((cartProduct) -> CompletableFuture.supplyAsync(() -> {
                    Optional<Product> product = productRepository.findById(cartProduct.getId());

                    return product
                            .filter(value -> value.getStockQuantity() >= cartProduct.getInCartQuantity())
                            .isPresent();

                }))
                .toList();

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        allFutures.join();

        return futures.stream().allMatch(CompletableFuture::join);
    }
}
