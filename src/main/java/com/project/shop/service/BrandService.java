package com.project.shop.service;

import com.project.shop.persistence.model.BrandEntity;
import com.project.shop.persistence.repository.BrandRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;

    public BrandEntity createBrand(BrandEntity brandEntity) {
        return brandRepository.save(brandEntity);
    }

    public Optional<BrandEntity> getByUuid(UUID brandId) {
        return brandRepository.findById(brandId);
    }

    public List<BrandEntity> getAll() {
        return brandRepository.findAll();
    }
}
