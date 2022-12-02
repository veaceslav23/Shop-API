package com.project.carrental.service.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequestDto {

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]{5,20}", message = "Password must contain more than 5 characters")
    private String password;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]{10,20}", message = "Username must be between 10 and 200 characters")
    private String username;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]{2,20}", message = "First name must contain more than 2 characters")
    private String firstName;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]{2,20}", message = "Last name must contain more than 2 characters")
    private String lastName;

    @NotNull
    @Email
    private String email;

    private String profilePicture;
}
