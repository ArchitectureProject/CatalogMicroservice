package com.efrei.catalogmicroservice.exception.custom;

public class BowlingParkMicroserviceException extends RuntimeException {
    public BowlingParkMicroserviceException(String message, Exception e) {
        super(message, e);
    }
}
