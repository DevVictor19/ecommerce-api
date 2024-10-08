package com.devvictor.ecommerce_api.application.use_cases.carts;

import com.devvictor.ecommerce_api.application.exceptions.NotFoundException;
import com.devvictor.ecommerce_api.application.services.CartService;
import com.devvictor.ecommerce_api.application.services.ProductService;
import com.devvictor.ecommerce_api.domain.entities.Cart;
import com.devvictor.ecommerce_api.domain.entities.CartProduct;
import com.devvictor.ecommerce_api.domain.entities.Product;
import com.devvictor.ecommerce_api.domain.factories.CartFactory;
import com.devvictor.ecommerce_api.domain.factories.CartProductFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AddProductToCartUseCase {
    private final CartService cartService;
    private final ProductService productService;

    public void execute(String productId, String userId, int quantity) {
        Optional<Product> product = productService.findById(productId);

        if (product.isEmpty()) {
            throw new NotFoundException("Product not found");
        }

        Optional<Cart> userCart = cartService.findByUserId(userId);

        CartProduct cartProduct = CartProductFactory.create(
                product.get().getId(),
                product.get().getPrice(),
                product.get().getName(),
                product.get().getDescription(),
                product.get().getPhotoUrl(),
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
