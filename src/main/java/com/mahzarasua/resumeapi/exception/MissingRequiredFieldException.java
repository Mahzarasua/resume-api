package com.mahzarasua.resumeapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MissingRequiredFieldException extends RuntimeException {

    private List<ExceptionBody.ErrorDetails> errorDetails = new ArrayList<>();

    public MissingRequiredFieldException(List<ExceptionBody.ErrorDetails> errorDetails, String errorMessage) {
        super(errorMessage);
        this.errorDetails = errorDetails;
    }

    public List<ExceptionBody.ErrorDetails> getErrorDetails() {
        return errorDetails;
    }
}
