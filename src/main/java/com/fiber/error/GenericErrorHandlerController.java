package com.fiber.error;

import com.fiber.error.excption.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GenericErrorHandlerController {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handle(Exception err) {
        log.error(err.getMessage(), err);
        return ErrorResponse.builder(err, HttpStatus.INTERNAL_SERVER_ERROR, err.getMessage()).build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorResponse resourceNotFound(ResourceNotFoundException err) {
        log.error(err.getMessage(), err);
        return ErrorResponse.builder(err, HttpStatus.NOT_FOUND, err.getMessage()).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse payloadError(MethodArgumentNotValidException err) {
        log.error(err.getMessage(), err);
        String message;
        if (!err.getBindingResult().getAllErrors().isEmpty()) {
            message = err.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        } else {
            message = err.getMessage();
        }
        return ErrorResponse.builder(err, HttpStatus.BAD_REQUEST, message).build();
    }

}
