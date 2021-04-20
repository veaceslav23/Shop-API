package com.project.shop.facade;

import com.project.shop.service.BrandService;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BrandFacade {
    private final BrandService brandService;
}
