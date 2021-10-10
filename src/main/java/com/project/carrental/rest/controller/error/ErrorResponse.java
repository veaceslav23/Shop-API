package com.project.carrental.rest.controller.error;

import java.sql.Timestamp;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ErrorResponse {

    private HttpStatus status;

    private List<String> messages;

    private Timestamp timestamp;

}
