package com.example.books.exceptions;

public class BookApplicationException extends RuntimeException {
    public BookApplicationException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public BookApplicationException(String exMessage) {
        super(exMessage);
    }
}