package com.bibliotex.catalog.exceptions;

import com.bibliotex.catalog.domain.dto.response.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {IllegalArgumentException.class, IllegalStateException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        var errorResponse = new ErrorResponse(HttpStatus.CONFLICT, ex.getMessage());

        return handleExceptionInternal(ex, errorResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value
            = {NoSuchElementException.class, BookNotFoundException.class, AuthorNotFoundException.class, PublisherNotFoundException.class})
    protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {
        var errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());

        return handleExceptionInternal(ex, errorResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, List<String>> errorsMap = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String name = this.getNameWithError(error);
            List<String> errorMessage = new LinkedList<>(Collections.singletonList(error.getDefaultMessage()));

            addContraintViolation(errorsMap, name, errorMessage);
            logger.warn(error.getDefaultMessage());
        });

        return handleExceptionInternal(ex, errorsMap,
                headers, status, request);
    }

    private String getNameWithError(ObjectError error) {
        return error instanceof FieldError fieldError ? fieldError.getField() : error.getObjectName();
    }

    private void addContraintViolation(Map<String, List<String>> errors, String fieldName, List<String> errorMessage) {
        if (errors.containsKey(fieldName)) {
            errors.merge(fieldName, errorMessage, (value, newValue) -> newValue.addAll(value) ? newValue : value);
        } else {
            errors.put(fieldName, errorMessage);
        }
    }
}
