package com.project.shop.persistence.repository;

import com.project.shop.persistence.model.BrandEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, UUID> {
}
