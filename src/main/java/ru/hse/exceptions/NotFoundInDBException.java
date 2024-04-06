package ru.hse.exceptions;

public class NotFoundInDBException extends RuntimeException {

    public NotFoundInDBException(String message) {
        super(message);
    }
}
