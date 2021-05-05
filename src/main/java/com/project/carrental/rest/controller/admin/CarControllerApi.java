package com.project.carrental.rest.controller.admin;

import com.project.carrental.facade.CarFacade;
import com.project.carrental.service.model.CarDto;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/admin/cars/")
@RequiredArgsConstructor
public class CarControllerApi {
    private final CarFacade carFacade;

    @GetMapping
    public ResponseEntity<Page<CarDto>> getAllCars(
        @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
        @RequestParam(name = "sort", defaultValue = "name", required = false) String sort,
        @RequestParam(name = "limit", defaultValue = "10", required = false) Integer limit,
        @RequestParam(name = "direction", defaultValue = "ASC", required = false) String direction
    ) {
        return ResponseEntity.ok(carFacade.getAll(page, limit, sort, direction));
    }

    @PostMapping
    public ResponseEntity<CarDto> create(@ModelAttribute CarDto carDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carFacade.create(carDto));
    }
}
