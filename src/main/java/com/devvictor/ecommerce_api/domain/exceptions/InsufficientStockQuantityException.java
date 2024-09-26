package com.devvictor.ecommerce_api.domain.exceptions;

public class InsufficientStockQuantityException extends RuntimeException {
    public InsufficientStockQuantityException() {
        super("The quantity to collect is not available in stock");
    }
}
