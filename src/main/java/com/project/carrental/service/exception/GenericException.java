package com.project.carrental.service.exception;

import lombok.Getter;

public class GenericException extends RuntimeException {
    @Getter
    private ExceptionType type;

    public GenericException(String message) {
        super(message);
    }

    public GenericException(ExceptionType type) {
        super(type.getMessage());
        this.type = type;
    }

    public static GenericException of(ExceptionType type) {
        return new GenericException(type);
    }
}
