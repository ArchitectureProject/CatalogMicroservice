package com.efrei.catalogmicroservice.exception.custom;

public class CatalogForBowlingIdAlreadyExistingException extends RuntimeException {
    public CatalogForBowlingIdAlreadyExistingException(String message) {
        super(message);
    }
}
