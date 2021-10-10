package com.project.carrental.service.validation;

import static com.project.carrental.service.exception.ExceptionType.INVALID_INVOICE_START_DATE_END_DATE;
import static com.project.carrental.service.exception.ExceptionType.USERNAME_IS_ALREADY_TAKEN;
import static com.project.carrental.service.exception.ExceptionType.USER_WITH_PROVIDED_EMAIL_EXISTS;

import com.project.carrental.persistence.model.InvoiceEntity;
import com.project.carrental.persistence.repository.UserRepository;
import com.project.carrental.service.exception.GenericException;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidationService {

    private final UserRepository userRepository;

    public void validateInvoiceStartEndDate(InvoiceEntity invoice) {
        val now = LocalDate.now();
        if (invoice.getStartDate().isBefore(now)
            || invoice.getEndDate().isBefore(now)
            || invoice.getEndDate().isBefore(invoice.getStartDate())
        ) {
            throw GenericException.of(INVALID_INVOICE_START_DATE_END_DATE);
        }
    }

    public void validateEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw GenericException.of(USER_WITH_PROVIDED_EMAIL_EXISTS);
        }
    }

    public void validateUsername(String username) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw GenericException.of(USERNAME_IS_ALREADY_TAKEN);
        }
    }
}
