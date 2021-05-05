package com.project.carrental.service;

import com.project.carrental.persistence.model.BrandEntity;
import com.project.carrental.persistence.repository.BrandRepository;

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

    public BrandEntity getByBrandName(String name) {
        return brandRepository.findByName(name);
    }
}
