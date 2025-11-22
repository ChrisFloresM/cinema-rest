package com.cfloresh.springboot.app.cinemarest.exception;

public class SeatNotAvaiableException extends RuntimeException {
    public SeatNotAvaiableException(String message) {
        super(message);
    }
}
