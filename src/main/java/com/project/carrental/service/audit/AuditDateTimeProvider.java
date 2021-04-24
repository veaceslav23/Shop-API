package com.project.carrental.service.audit;

import com.project.carrental.service.DateTimeService;

import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.stereotype.Component;

import java.time.temporal.TemporalAccessor;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuditDateTimeProvider implements DateTimeProvider {

    private final DateTimeService dateTimeService;

    @Override
    public Optional<TemporalAccessor> getNow() {
        return Optional.of(dateTimeService.getCurrentTimeWithTimeZone());
    }
}
