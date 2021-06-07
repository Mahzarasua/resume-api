package com.mahzarasua.resumeapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MissingRequiredFieldException extends RuntimeException {
    public MissingRequiredFieldException(String exceptionDetails) {
        super(exceptionDetails);
    }
}
