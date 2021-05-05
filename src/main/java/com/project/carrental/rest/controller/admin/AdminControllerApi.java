package com.project.carrental.rest.controller.admin;

import com.project.carrental.facade.CarFacade;
import com.project.carrental.persistence.model.CarEntity;
import com.project.carrental.service.CarService;
import com.project.carrental.service.UserService;
import com.project.carrental.service.model.AdminUserDto;
import com.project.carrental.service.model.CarDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import lombok.RequiredArgsConstructor;

import static com.project.carrental.service.utils.TransformersUtils.convertFromUserToAdminUserDto;

@RestController
@RequestMapping(value = "/api/admin/")
@RequiredArgsConstructor
public class AdminControllerApi {

    private final UserService userService;
    private final CarFacade carFacade;

    @GetMapping(value = "users/{id}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok(convertFromUserToAdminUserDto.apply(userService.getById(id)));
    }

    @PostMapping(value = "/cars")
    public ResponseEntity<CarDto> addCar(
        @RequestBody CarDto carEntity
    ) {
        return ResponseEntity.ok(carFacade.create(carEntity));
    }
}
