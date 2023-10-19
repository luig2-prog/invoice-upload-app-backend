package com.fev.csvprocessor.domain.common.exception;

public class DefaultCustomException extends RuntimeException{
    public DefaultCustomException(String message) {
        super(message);
    }

    public DefaultCustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
