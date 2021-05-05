package com.project.carrental.rest.controller.user;

import com.project.carrental.facade.UserFacade;
import com.project.carrental.service.UserService;
import com.project.carrental.service.model.UserDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import lombok.RequiredArgsConstructor;

import static com.project.carrental.service.utils.TransformersUtils.convertFromUserToUserDto;

@RestController
@RequestMapping(value = "/api/users/")
@RequiredArgsConstructor
public class UserControllerApi {
    private final UserService userService;
    private final UserFacade userFacade;

    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok(convertFromUserToUserDto.apply(userService.getById(id)));
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<UserDto> updateUserById(
        @PathVariable(name = "id") UUID id,
        @RequestBody UserDto userDto
    ) {
        return ResponseEntity.ok(userFacade.update(id, userDto));
    }
}
