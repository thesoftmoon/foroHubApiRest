package com.tomaspacheco.foroHubApiRest.infra.errors;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity errorHandler404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity errorHandler400(MethodArgumentNotValidException e){
        var errors = e.getFieldErrors().stream().map(ErrorDataValidationDTO::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    /*Este constructor y DTO maneja la salida del mensaje de error*/
    private record ErrorDataValidationDTO(String campo, String error){
        public ErrorDataValidationDTO(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
