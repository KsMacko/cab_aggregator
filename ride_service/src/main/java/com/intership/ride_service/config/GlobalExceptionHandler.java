package com.intership.ride_service.config;


import com.intership.ride_service.config.exception.BaseExceptionDto;
import com.intership.ride_service.config.exception.BaseValidationException;
import com.intership.ride_service.config.exception.InvalidInputException;
import com.intership.ride_service.config.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseValidationException> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> messageSource.getMessage(
                        fieldError.getDefaultMessage(),
                        null,
                        LocaleContextHolder.getLocale()))
                .toList();
        return new ResponseEntity<>(new BaseValidationException(messageSource.getMessage(
                "error.invalidInput",
                null,
                LocaleContextHolder.getLocale()), errors), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler( ResourceNotFoundException.class)
    public ResponseEntity<BaseExceptionDto> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(new BaseExceptionDto(messageSource.getMessage(
                ex.getMessage(),
                null,
                LocaleContextHolder.getLocale())), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<BaseExceptionDto> handleInvalidInputExceptions(Exception ex) {
        return new ResponseEntity<>(new BaseExceptionDto(messageSource.getMessage(
                ex.getMessage(),
                null,
                LocaleContextHolder.getLocale())), HttpStatus.BAD_REQUEST);
    }
}
