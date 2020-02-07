package com.stratij.proxy.handler;

import com.stratij.proxy.controller.ProxyController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(ProxyController.class);

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleWrongArguments(MethodArgumentTypeMismatchException e) {
        logger.warn(e.getMessage());
        return new ResponseEntity<>("Input isn't valid due to validation error: " + e.getMessage()
                + " ---> Please maximum fibonacci number", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleWrongArguments(ConstraintViolationException e) {
        logger.warn(e.getMessage());
        return new ResponseEntity<>("Input isn't valid due to validation error: " + e.getMessage()
                + " ---> Number shoul be 0 or greater", HttpStatus.BAD_REQUEST);
    }
}
