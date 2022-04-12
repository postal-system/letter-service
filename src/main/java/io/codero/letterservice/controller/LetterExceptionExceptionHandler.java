package io.codero.letterservice.controller;

import io.codero.letterservice.exception.IdAlreadyExistException;
import io.codero.letterservice.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class LetterExceptionExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(IdAlreadyExistException.class)
    public ResponseEntity<?> handleIdAlreadyExistException(IdAlreadyExistException exception) {
        return ResponseEntity.status(409).body(exception.getMessage());
    }
}
