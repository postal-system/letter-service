package io.codero.letterservice.exception;

public class IdAlreadyExistException extends RuntimeException {
    public IdAlreadyExistException(String message) {
        super(message);
    }
}
