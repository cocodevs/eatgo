package com.dadongs.eatgo.application;

public class EmailNotExistException extends RuntimeException {
    EmailNotExistException(String email) {
        super("Email is not registered" + email);
    }
}
