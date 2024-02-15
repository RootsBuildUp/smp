package com.smp.coremodule.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {

    private LocalDateTime timestamp;

    private int status;

    private String error;

    private String message;

    private Object body;

    public ApiError(HttpStatus httpStatus, String errorMessage){
        this.timestamp = LocalDateTime.now();
        this.status = httpStatus.value();
        this.error = httpStatus.toString();
        this.message = errorMessage;
    }

    public ApiError(HttpStatus httpStatus, String errorMessage, Object body){
        this.timestamp = LocalDateTime.now();
        this.status = httpStatus.value();
        this.error = httpStatus.toString();
        this.message = errorMessage;
        this.body = body;
    }
}
