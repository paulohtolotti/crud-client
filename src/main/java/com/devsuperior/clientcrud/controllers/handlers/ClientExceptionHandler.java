package com.devsuperior.clientcrud.controllers.handlers;

import com.devsuperior.clientcrud.dto.BaseError;
import com.devsuperior.clientcrud.services.exceptions.ContentNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ClientExceptionHandler {

    @ExceptionHandler(ContentNotFoundException.class)
    public ResponseEntity<BaseError> contentNotFound(ContentNotFoundException err, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        BaseError error = new BaseError(Instant.now(), status.value(), err.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }
}
