package com.project.carrental.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.carrental.persistence.model.ImageEntity;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    @NotNull
    private UUID id;
    @NotNull
    private String username;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private String imageCode;
    private MultipartFile image;
}
