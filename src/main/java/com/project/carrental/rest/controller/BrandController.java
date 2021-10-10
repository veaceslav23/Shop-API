package com.project.carrental.rest.controller;

import static com.project.carrental.service.utils.TransformersUtils.convertToBrandDto;

import com.project.carrental.service.BrandService;
import com.project.carrental.service.model.BrandDto;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(brandService.getAll().stream().map(convertToBrandDto).collect(Collectors.toList()));
    }

    @GetMapping("/{code}")
    public ResponseEntity<BrandDto> getByCode(@PathVariable("code") String code) {
        return ResponseEntity.ok(convertToBrandDto.apply(brandService.getByBrandName(code)));
    }
}
