package com.fiber.error;

import com.fiber.error.excption.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GenericErrorHandlerController {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handle(Exception err) {
        return ErrorResponse.builder(err, HttpStatus.INTERNAL_SERVER_ERROR, err.getMessage()).build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorResponse resourceNotFound(ResourceNotFoundException err) {
        return ErrorResponse.builder(err, HttpStatus.NOT_FOUND, err.getMessage()).build();
    }

}
