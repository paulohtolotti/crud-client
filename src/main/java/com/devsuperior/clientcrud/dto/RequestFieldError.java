package com.devsuperior.clientcrud.dto;

public class RequestFieldError {
    private String fieldName;
    private String fieldErrorMessage;

    public RequestFieldError() {

    }

    public RequestFieldError(String fieldName, String fieldErrorMessage) {
        this.fieldName = fieldName;
        this.fieldErrorMessage = fieldErrorMessage;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldErrorMessage() {
        return fieldErrorMessage;
    }
}
