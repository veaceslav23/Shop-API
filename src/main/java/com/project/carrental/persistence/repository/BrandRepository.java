package com.project.carrental.persistence.repository;

import com.project.carrental.persistence.model.BrandEntity;
import com.project.carrental.service.BrandService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, UUID> {
    BrandEntity findByName(String name);
}
