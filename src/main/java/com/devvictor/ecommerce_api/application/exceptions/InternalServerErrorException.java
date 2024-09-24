package com.devvictor.ecommerce_api.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException() {
        super("Internal server error");
    }

    public InternalServerErrorException(String message) {
        super(message);
    }
}
