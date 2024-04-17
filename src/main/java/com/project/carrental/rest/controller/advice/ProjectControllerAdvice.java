package com.project.carrental.rest.controller.advice;

import static java.util.Collections.singletonList;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import com.project.carrental.rest.controller.error.ErrorResponse;
import com.project.carrental.service.DateTimeService;
import com.project.carrental.service.exception.GenericException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class ProjectControllerAdvice {

    /**
     * Handle known exception response entity.
     *
     * @param exception the ex.
     * @return the response entity.
     */
    @ExceptionHandler(value = {GenericException.class})
    public ResponseEntity<ErrorResponse> appEntityNotFoundExceptionHandler(GenericException exception) {
        var status = HttpStatus.valueOf(exception.getType().getStatus());

        var errorResponse = ErrorResponse.builder()
            .status(status)
            .messages(Collections.singletonList(exception.getMessage()))
            .timestamp(new Timestamp(System.currentTimeMillis()))
            .build();

        return ResponseEntity.status(status).body(errorResponse);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
        MethodArgumentNotValidException ex
    ) {
        var errorMessages = ex.getBindingResult().getAllErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.toList());

        var errorResponse = ErrorResponse.builder()
            .status(BAD_REQUEST)
            .messages(errorMessages)
            .timestamp(new Timestamp(System.currentTimeMillis()))
            .build();

        return ResponseEntity.status(BAD_REQUEST).body(errorResponse);
    }


    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MailException.class)
    public ResponseEntity<ErrorResponse> handleMailException(
        MailException ex
    ) {
        var errorMessages = singletonList(ex.getLocalizedMessage());

        var errorResponse = ErrorResponse.builder()
            .status(INTERNAL_SERVER_ERROR)
            .messages(errorMessages)
            .timestamp(new Timestamp(System.currentTimeMillis()))
            .build();

        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
