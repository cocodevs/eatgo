package com.dadongs.eatgo.application;

public class PasswordWrongException extends RuntimeException {
    PasswordWrongException() {
        super("Password is wrong");
    }
}
