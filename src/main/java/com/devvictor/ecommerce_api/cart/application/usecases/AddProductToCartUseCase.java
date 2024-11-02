package com.devvictor.ecommerce_api.cart.application.usecases;

import com.devvictor.ecommerce_api.shared.application.exceptions.BadRequestException;
import com.devvictor.ecommerce_api.shared.application.exceptions.NotFoundException;
import com.devvictor.ecommerce_api.cart.application.services.CartService;
import com.devvictor.ecommerce_api.products.application.services.ProductService;
import com.devvictor.ecommerce_api.cart.domain.entities.Cart;
import com.devvictor.ecommerce_api.cart.domain.entities.CartProduct;
import com.devvictor.ecommerce_api.products.domain.entities.Product;
import com.devvictor.ecommerce_api.cart.domain.factories.CartFactory;
import com.devvictor.ecommerce_api.cart.domain.factories.CartProductFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AddProductToCartUseCase {
    private final CartService cartService;
    private final ProductService productService;

    public void execute(String productId, String userId, int quantity) {
        Product product = productService.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found"));

        if (product.getStockQuantity() < quantity) {
            throw new BadRequestException("Insufficient quantity of product in stock");
        }

        Optional<Cart> userCart = cartService.findByUserId(userId);

        CartProduct cartProduct = CartProductFactory.create(
                product.getId(),
                product.getPrice(),
                product.getName(),
                product.getDescription(),
                product.getPhotoUrl(),
                quantity
        );

        if (userCart.isEmpty()) {
            Cart newCart = CartFactory.create(userId);
            newCart.addProduct(cartProduct);

            cartService.create(newCart);

            return;
        }

        userCart.get().addProduct(cartProduct);
        cartService.update(userCart.get());
    }
}
