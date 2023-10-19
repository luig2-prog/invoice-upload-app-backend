package com.fev.csvprocessor.application.conf;

import com.fev.csvprocessor.domain.common.exception.CustomIllegalArgumentException;
import com.fev.csvprocessor.domain.common.exception.DefaultCustomException;
import com.fev.csvprocessor.infrastructure.common.dto.ResponseDto;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class CustomControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {CustomIllegalArgumentException.class})
    protected ResponseEntity<Object> handleAppException(CustomIllegalArgumentException ex, WebRequest request) {
        log.error("{}", ex.getMessage());
        log.error("input: {}", ex.getArgument());
        return handleExceptionInternal(ex, new ResponseDto<>(null, ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {DefaultCustomException.class})
    protected ResponseEntity<Object> handleAppException(DefaultCustomException ex, WebRequest request) {
        log.error("{}", ex.getMessage());
        return handleExceptionInternal(ex, new ResponseDto<>(null, ex.getMessage()), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @NonNull HttpHeaders headers,@NonNull HttpStatusCode status,@NonNull WebRequest request) {
        log.error("{}", ex.getMessage());
        StringBuilder errors = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            log.error("Invalid {} value submitted for {}", fieldError.getRejectedValue(), fieldError.getField());
            errors.append(String.format(Objects.requireNonNull(fieldError.getDefaultMessage()), fieldError.getField())).append(" | ");
        });
        return handleExceptionInternal(ex, new ResponseDto<>(null, errors.toString()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {
            Exception.class
    })
    protected ResponseEntity<Object> othersExceptions(Exception ex, WebRequest request) {
        ex.printStackTrace();
        log.error("not controlled exception {}", ex.getMessage());
        return handleExceptionInternal(ex, new ResponseDto<>(null, ex.getMessage()), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
