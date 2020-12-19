package com.example.books.exceptions;

public class DemoApplicationException extends RuntimeException {
    public DemoApplicationException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public DemoApplicationException(String exMessage) {
        super(exMessage);
    }
}