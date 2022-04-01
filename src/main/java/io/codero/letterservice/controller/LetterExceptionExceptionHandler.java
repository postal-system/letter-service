package io.codero.letterservice.controller;

import io.codero.letterservice.exception.CastIdAlreadyExistException;
import io.codero.letterservice.exception.CastNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class LetterExceptionExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CastNotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(CastNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }


    @ExceptionHandler(CastIdAlreadyExistException.class)
    public ResponseEntity<?> handleIdAlreadyExistException(CastIdAlreadyExistException exception) {
        return ResponseEntity.status(409).body(exception.getMessage());
    }
}
