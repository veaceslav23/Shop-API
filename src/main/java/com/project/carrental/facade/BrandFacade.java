package com.project.carrental.facade;

import com.project.carrental.service.BrandService;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BrandFacade {
    private final BrandService brandService;
}
