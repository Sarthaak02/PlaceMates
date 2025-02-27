package com.placemates.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;
import com.placemates.response.APIResponse;
import java.time.LocalDateTime;
import java.util.*;



@ControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<String> messages = new ArrayList<>();

        for (FieldError fieldError : fieldErrors) {
            messages.add(fieldError.getDefaultMessage());
        }

        APIResponse APIResponse = new APIResponse(
                LocalDateTime.now(),
                messages
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(APIResponse);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIResponse> handleConstraintViolationException(ConstraintViolationException ex) {

        List<String> messages = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            messages.add(violation.getMessage());
        }

        APIResponse APIResponse = new APIResponse(
                LocalDateTime.now(),
                messages
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(APIResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<APIResponse> handleMissingRequestBody(HttpMessageNotReadableException ex) {
        APIResponse response = new APIResponse(LocalDateTime.now(), Collections.singletonList("Request body is missing or invalid."));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(DataAlreadyExistsException.class)
    public ResponseEntity<APIResponse> handleDataAlreadyExistsException(DataAlreadyExistsException ex) {

        List<String> message = List.of(ex.getMessage());
        APIResponse APIResponse = new APIResponse(
                LocalDateTime.now(),
                message
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(APIResponse);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<APIResponse> handleNoResultFoundException(DataNotFoundException ex){
        List<String> message = List.of(ex.getMessage());
        APIResponse APIResponse = new APIResponse(
                LocalDateTime.now(),
                message
        );
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(APIResponse);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<APIResponse> handleException(Exception ex){
//        List<String> message = List.of(ex.getMessage());
//        APIResponse APIResponse = new APIResponse(
//                LocalDateTime.now(),
//                message
//        );
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(APIResponse);
//    }
}
