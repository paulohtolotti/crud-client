package com.devsuperior.clientcrud.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class DataValidationError extends BaseError {

    private List<RequestFieldError> errorList = new ArrayList<>();

    public DataValidationError() {
    }

    public DataValidationError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public List<RequestFieldError> getErrorList() {
        return errorList;
    }

    public void addError (RequestFieldError err) {
        errorList.add(err);
    }

}
