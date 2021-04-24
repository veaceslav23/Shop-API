package com.project.carrental.rest.controller.admin;

import com.project.carrental.service.UserService;
import com.project.carrental.service.model.AdminUserDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import lombok.RequiredArgsConstructor;

import static com.project.carrental.service.utils.TransformersUtils.convertFromUserToAdminUserDto;

@RestController
@RequestMapping(value = "/api/admin/")
@RequiredArgsConstructor
public class AdminControllerApi {

    private final UserService userService;

    @GetMapping(value = "users/{id}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok(convertFromUserToAdminUserDto.apply(userService.getById(id)));
    }
}
