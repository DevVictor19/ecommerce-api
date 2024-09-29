package com.devvictor.ecommerce_api.domain.entities;

import com.devvictor.ecommerce_api.domain.exceptions.InvalidEntityOperationException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "carts")
public class Cart {
    @Id
    private String id;

    @Indexed(unique = true)
    @Field(name = "user_id")
    private String userId;

    private List<CartProduct> products = new ArrayList<>();

    @Field(name = "products_qnt")
    private int productsQuantity;

    @Field(name = "total_price")
    private double totalPrice;

    @Field(name = "created_at", targetType = FieldType.DATE_TIME)
    private Date createdAt;

    public void addProduct(CartProduct product) {
       Optional<CartProduct> existentProduct = products.stream()
               .filter(p -> p.getId().equals(product.getId()))
               .findFirst();

       if (existentProduct.isPresent()) {
           existentProduct.get().addInCartQuantity(product.getInCartQuantity());
       } else {
           products.add(product);
       }

       double priceToAdd = product.getPrice() * product.getInCartQuantity();

       addTotalPrice(priceToAdd);
       addProductsQuantity(product.getInCartQuantity());
    }

    public void subtractProduct(String productId, int quantityToSub) {
        Optional<CartProduct> existentProduct = products.stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst();

        if (existentProduct.isEmpty()) {
            throw new InvalidEntityOperationException("Product is not present in cart to subtract it");
        }

        existentProduct.get().subtractInCartQuantity(quantityToSub);

        if (existentProduct.get().getInCartQuantity() == 0) {
            products.remove(existentProduct.get());
        }

        double priceToSubtract = existentProduct.get().getPrice() * quantityToSub;

        subtractTotalPrice(priceToSubtract);
        subtractProductsQuantity(quantityToSub);
    }

    public void clearProducts() {
        products.clear();
        totalPrice = 0;
        productsQuantity = 0;
    }

    private void addTotalPrice(double priceToAdd) {
        if (priceToAdd <= 0) {
            throw new InvalidEntityOperationException("Price to add must be a positive value");
        }

        totalPrice += priceToAdd;
    }

    private void subtractTotalPrice(double priceToSubtract) {
        if (totalPrice - priceToSubtract < 0) {
            throw new InvalidEntityOperationException("Total price cannot be less than zero");
        }

        totalPrice -= priceToSubtract;
    }

    private void addProductsQuantity(int quantityToAdd) {
        if (quantityToAdd <= 0) {
            throw new InvalidEntityOperationException("Quantity to add must be a positive value");
        }

        if (productsQuantity + quantityToAdd > 30) {
            throw new InvalidEntityOperationException("Quantity to add must not pass the maximum cart capacity");
        }

        productsQuantity += quantityToAdd;
    }

    private void subtractProductsQuantity(int quantityToSub) {
        if (productsQuantity - quantityToSub < 0) {
            throw new InvalidEntityOperationException("Quantity to subtract must not pass the minimum cart capacity");
        }

        productsQuantity -= quantityToSub;
    }
}
