package com.project.carrental.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Set;
import java.util.UUID;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private UUID id;
    @NotNull
    @Size(min = 3, max = 20, message
        = "Username must be between 10 and 200 characters")
    private String username;

    @NotNull
    @Size(min = 2, message
        = "First name must contain more than 2 characters")
    private String firstName;

    @NotNull
    @Size(min = 2, message
        = "Last name must contain more than 2 characters")
    private String lastName;

    @Email(message = "Email should be valid")
    private String email;

    @Size(min = 5,
        max = 20,
        message = "Password must contain more than 5 characters")
    private String password;
    private String imageCode;
    private MultipartFile image;
    private Set<InvoiceDto> invoices;
}
