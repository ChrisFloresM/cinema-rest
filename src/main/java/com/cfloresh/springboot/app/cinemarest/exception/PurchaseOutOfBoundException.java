package com.cfloresh.springboot.app.cinemarest.exception;

public class PurchaseOutOfBoundException extends RuntimeException {
    public PurchaseOutOfBoundException(String message) {
        super(message);
    }
}
