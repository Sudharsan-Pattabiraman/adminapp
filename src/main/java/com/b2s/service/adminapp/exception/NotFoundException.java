package com.b2s.service.adminapp.exception;

/**
 * Created by spattabiraman on 10/19/2018.
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
