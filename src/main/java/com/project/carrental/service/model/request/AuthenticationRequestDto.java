package com.project.carrental.service.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AuthenticationRequestDto {

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]{2,20}")
    private String username;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]{2,20}")
    private String password;
}
