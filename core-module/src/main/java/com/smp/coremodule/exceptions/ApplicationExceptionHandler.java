package com.smp.coremodule.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ApiError> handleObjectNotFoundException(ObjectNotFoundException notFoundException){
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, notFoundException.getMessage());
        return new ResponseEntity<ApiError>(apiError, HttpStatus.NOT_FOUND);
    }

}
