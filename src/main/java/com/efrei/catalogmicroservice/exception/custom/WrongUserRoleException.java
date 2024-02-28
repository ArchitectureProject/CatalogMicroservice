package com.efrei.catalogmicroservice.exception.custom;

public class WrongUserRoleException extends RuntimeException {
    public WrongUserRoleException(String message) {
        super(message);
    }
}
