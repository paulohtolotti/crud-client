package com.devsuperior.clientcrud.controllers.handlers;

import com.devsuperior.clientcrud.dto.BaseError;
import com.devsuperior.clientcrud.dto.DataValidationError;
import com.devsuperior.clientcrud.dto.RequestFieldError;
import com.devsuperior.clientcrud.services.exceptions.ContentNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseError> dataValidation(MethodArgumentNotValidException err, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        DataValidationError validationError = new DataValidationError(Instant.now(), status.value(),
                "Erro na validação de dados", request.getRequestURI());

        // Itera sobre todos os erros de validação e inclui na lista de erros do DTO customizado
        //err.getBindingResult().getFieldErrors().forEach( e -> validationError.addError(new RequestFieldError(e.getField(), e.getDefaultMessage()) ) );

        for(FieldError e : err.getBindingResult().getFieldErrors()) {
            validationError.addError(new RequestFieldError(e.getField(), e.getDefaultMessage()));
        }
        return ResponseEntity.status(status).body(validationError);
    }
}
