package com.project.carrental.rest.controller;

import com.project.carrental.service.RevenueTypeService;
import com.project.carrental.service.model.RevenueTypeDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/revenue/")
public class RevenueTypeControllerApi {

    private final RevenueTypeService revenueTypeService;

    @GetMapping
    public ResponseEntity<List<RevenueTypeDto>> getAll() {
        return ResponseEntity.ok(revenueTypeService.getAll());
    }
}
