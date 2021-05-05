package com.project.carrental.service.model;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String username;
    private String password;
}
