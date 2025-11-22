package com.cfloresh.springboot.app.cinemarest.exception;

import com.cfloresh.springboot.app.cinemarest.error.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({PurchaseOutOfBoundException.class, SeatNotAvaiableException.class})
    public ResponseEntity<ErrorMessage> handlePurchaseException(RuntimeException e) {
        return ResponseEntity.badRequest().body(new ErrorMessage(e.getMessage()));
    }

    @ExceptionHandler(PurchaseNotFoundException.class)
    public ResponseEntity<ErrorMessage> handlePurchaseNotFoundException(PurchaseNotFoundException e) {
        return new ResponseEntity<>(new ErrorMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
