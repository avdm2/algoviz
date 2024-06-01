package ru.hse.exceptions;

public class EmptyRequiredFieldsException extends RuntimeException {

    public EmptyRequiredFieldsException(String message) {
        super(message);
    }
}
