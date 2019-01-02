package com.b2s.service.adminapp.exception;

/**
 * Created by spattabiraman on 12/13/2018.
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException() {
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
