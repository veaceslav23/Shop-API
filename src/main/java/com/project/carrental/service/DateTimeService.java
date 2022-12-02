package com.project.carrental.service;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class DateTimeService {
    private static final ZoneOffset DEFAULT_TIME_ZONE = ZoneOffset.UTC;

    public ZonedDateTime getCurrentTimeWithTimeZone() {
        return ZonedDateTime.now(DEFAULT_TIME_ZONE);
    }

    public ZonedDateTime getTimeWithTimeZone(Instant time) {
        return time.atZone(DEFAULT_TIME_ZONE);
    }
}
