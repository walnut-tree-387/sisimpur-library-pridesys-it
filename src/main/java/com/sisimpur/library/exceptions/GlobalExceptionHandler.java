package com.sisimpur.library.exceptions;

import com.sisimpur.library.exceptions.types.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException e) {
        Map<String,Object> response = new HashMap<>();
        response.put("code", HttpStatus.NOT_FOUND.value());
        response.put("Error message", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleInvalidMethodArgumentException(MethodArgumentNotValidException e) {
        Map<String,Object> response = new HashMap<>();
        response.put("code", HttpStatus.BAD_REQUEST.value());
        Map<String, String> fieldErrors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error ->
                fieldErrors.put(error.getField(), error.getDefaultMessage())
        );

        response.put("errors", fieldErrors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}