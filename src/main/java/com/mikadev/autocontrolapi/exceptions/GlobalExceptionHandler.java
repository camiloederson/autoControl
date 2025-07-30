package com.mikadev.autocontrolapi.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleValidationErrors(MethodArgumentNotValidException ex,
                                                           HttpServletResponse response,
                                                           HttpServletRequest request){
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(
                (error) -> errorMap.put(
                        error.getField(), error.getDefaultMessage()
                )
        );
        ErrorDTO apiErrorValidationDTO = new ErrorDTO(
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                request.getMethod(),
                request.getRequestURI(),
                ex.getMessage(),
                errorMap,
                "Error thrown from ValidationErrors"
                );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorValidationDTO);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleObjectNotFoundException(ResourceNotFoundException ex,
                                                                  HttpServletRequest request){
        ErrorDTO errorDTO = new ErrorDTO(
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                request.getMethod(),
                request.getRequestURI(),
                ex.getMessage(),
                new HashMap<>(),
                "Error thrown from ResourceNotFound"
                );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleGenericExceptions(Exception ex, HttpServletRequest request){
        ErrorDTO errorDTO = new ErrorDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now(),
                request.getMethod(),
                request.getRequestURI(),
                ex.getMessage(),
                new HashMap<>(),
                "Error thrown from GenericErrorException");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);
    }
}
