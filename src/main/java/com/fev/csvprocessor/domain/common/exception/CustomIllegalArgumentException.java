package com.fev.csvprocessor.domain.common.exception;

import lombok.Getter;

@Getter
public class CustomIllegalArgumentException extends DefaultCustomException {
    private final String method;
    private final Object argument;

    public CustomIllegalArgumentException(String message, String method, Object argument) {
        super(message);
        this.method = method;
        this.argument = argument;
    }
}
