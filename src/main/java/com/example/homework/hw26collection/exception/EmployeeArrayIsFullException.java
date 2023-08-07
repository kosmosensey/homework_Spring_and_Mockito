package com.example.homework.hw26collection.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeArrayIsFullException extends RuntimeException {
    public EmployeeArrayIsFullException() {
    }

    public EmployeeArrayIsFullException(String message) {
        super(message);
    }

    public EmployeeArrayIsFullException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeArrayIsFullException(Throwable cause) {
        super(cause);
    }

    public EmployeeArrayIsFullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
