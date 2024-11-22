package com.bs.bookStore.exceptions;

public class InvalidIsbnException extends RuntimeException {
    public InvalidIsbnException(String message) {
        super(message);
    }
}
