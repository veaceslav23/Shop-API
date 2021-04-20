package com.project.shop.service.utils;

import com.project.shop.persistence.model.BrandEntity;
import com.project.shop.service.model.BrandDto;
import com.project.shop.service.model.BrandRequestDto;

import java.util.function.Function;

public class TransformersUtils {
    public static final Function<BrandEntity, BrandDto> convertToBrandDto =
        brand -> BrandDto.builder()
        .id(brand.getId())
        .name(brand.getName())
        .build();

    public static final Function<BrandRequestDto, BrandDto> convertFromBrandRequestToDto =
        brandRequestDto -> BrandDto.builder()
        .name(brandRequestDto.getName())
        .build();
}
