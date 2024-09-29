package com.devvictor.ecommerce_api.domain.exceptions;

public class InvalidEntityOperationException extends DomainException {
    public InvalidEntityOperationException(String message) {
        super(message);
    }

    public InvalidEntityOperationException() {
        super("Error while trying to execute an entity operation");
    }
}
