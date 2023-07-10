package com.kata.bankaccount.buisness.exception;

public class BuisnessException extends Exception {

    private final String message;

    @Override
    public String getMessage() {
        return message;
    }

    public BuisnessException(String message) {
        this.message=message;


    }
}
