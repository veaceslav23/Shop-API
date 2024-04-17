package com.project.carrental.rest.controller.admin;

import com.project.carrental.facade.CarFacade;
import com.project.carrental.service.model.CarDto;
import com.project.carrental.service.model.request.GetAllCarsRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/cars/")
public class CarControllerApi {

    private final CarFacade carFacade;

    @GetMapping
    public ResponseEntity<Page<CarDto>> getAllCars(
        @RequestParam(name = "page",
            defaultValue = "0",
            required = false) Integer page,
        @RequestParam(name = "sort",
            defaultValue = "model",
            required = false) String sort,
        @RequestParam(name = "limit",
            defaultValue = "3",
            required = false) Integer limit,
        @RequestParam(name = "direction",
            defaultValue = "ASC",
            required = false) String direction,
        @RequestParam(name = "brandId",
            defaultValue = "",
            required = false) List<String> brandId,
        @RequestParam(name = "modelId",
            defaultValue = "",
            required = false) List<String> modelId,
        @RequestParam(name = "transmission",
            defaultValue = "",
            required = false) List<String> transmission,
        @RequestParam(name = "fuelType",
            defaultValue = "",
            required = false) List<String> fuelType,
        @RequestParam(name = "traction",
            defaultValue = "",
            required = false) List<String> traction,
        @RequestParam(name = "minYear",
            defaultValue = "",
            required = false) Integer minYear,
        @RequestParam(name = "maxYear",
            defaultValue = "",
            required = false) Integer maxYear,
        @RequestParam(name = "minPrice",
            defaultValue = "",
            required = false) BigDecimal minPrice,
        @RequestParam(name = "maxPrice",
            defaultValue = "",
            required = false) BigDecimal maxPrice
    ) {
        var request = GetAllCarsRequest.builder()
            .brandId(brandId)
            .model(modelId)
            .transmission(transmission)
            .fuelType(fuelType)
            .traction(traction)
            .minYear(Optional.ofNullable(minYear))
            .maxYear(Optional.ofNullable(maxYear))
            .minPrice(Optional.ofNullable(minPrice))
            .maxPrice(Optional.ofNullable(maxPrice))
            .build();

        return ResponseEntity.ok(carFacade.getAll(page, limit, sort, direction, request));
    }

    @GetMapping("{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(carFacade.getById(id));
    }

    @PostMapping
    public ResponseEntity<CarDto> create(@Valid @ModelAttribute CarDto carDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carFacade.create(carDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<CarDto> updateCarById(@PathVariable("id") UUID id, @ModelAttribute CarDto carDto) {
        return ResponseEntity.ok(carFacade.updateByID(id, carDto));
    }
}
