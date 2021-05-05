package com.project.carrental.service.model;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequestDto {
    @NotNull
    private String password;
    @NotNull
    private String username;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    private String profilePicture;
}
