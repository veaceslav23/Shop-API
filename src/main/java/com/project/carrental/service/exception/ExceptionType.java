package com.project.carrental.service.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ExceptionType {

    CAR_SHOULD_NOT_BE_NULL("Car description should not be null", 400),

    CAR_MODEL_IS_MANDATORY("Car Model is mandatory!", 400),

    INVOICE_NOT_FOUND("Invoice not found", 404),

    REVENUE_TYPE_NOT_FOUND("Revenue Type not found", 404),

    CAR_NOT_FOUND("Car not found", 404),

    INVALID_USERNAME_OR_PASSWORD("Invalid username or password", 404),

    FAILED_TO_UPLOAD_IMAGE("Failed to upload image", 500),

    BRAND_NOT_FOUND("Brand not found", 404),

    INVALID_INVOICE_START_DATE_END_DATE("Invalid start date and end date", 400),

    USER_WITH_PROVIDED_EMAIL_EXISTS("User with provided email already exists", 400),

    USERNAME_IS_ALREADY_TAKEN("Username is already taken", 400),

    USER_NOT_FOUND("User not found", 404);

    private final String message;
    private final int status;
}
