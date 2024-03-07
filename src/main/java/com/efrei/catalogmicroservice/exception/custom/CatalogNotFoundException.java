package com.efrei.catalogmicroservice.exception.custom;

public class CatalogNotFoundException extends RuntimeException{
    public CatalogNotFoundException(String message) {
        super(message);
    }
}
