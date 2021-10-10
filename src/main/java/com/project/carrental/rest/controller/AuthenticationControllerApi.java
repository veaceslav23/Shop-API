package com.project.carrental.rest.controller;

import static com.project.carrental.service.exception.ExceptionType.INVALID_USERNAME_OR_PASSWORD;
import static com.project.carrental.service.utils.TransformersUtils.convertFromRegisterRequestDtoToUser;
import static com.project.carrental.service.utils.TransformersUtils.convertFromUserToUserDto;

import com.project.carrental.facade.UserFacade;
import com.project.carrental.service.UserService;
import com.project.carrental.service.exception.GenericException;
import com.project.carrental.service.model.request.AuthenticationRequestDto;
import com.project.carrental.service.model.request.RegisterRequestDto;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth/")
public class AuthenticationControllerApi {

    private final UserService userService;
    private final UserFacade userFacade;

    @PostMapping(
        value = "login",
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity login(@Valid @RequestBody AuthenticationRequestDto authenticationRequest) {
        try {
            return ResponseEntity.ok().body(userFacade.login(authenticationRequest));
        } catch (AuthenticationException e) {
            throw GenericException.of(INVALID_USERNAME_OR_PASSWORD);
        }
    }

    @PostMapping(
        value = "register",
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity register(
        @Valid @RequestBody RegisterRequestDto registerRequestDto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userFacade.register(registerRequestDto));
    }
}
