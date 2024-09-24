package com.devvictor.ecommerce_api.infra.handlers;

import java.util.Date;

public record ExceptionResponse(
        Date timestamp,
        String message,
        String details) {
}