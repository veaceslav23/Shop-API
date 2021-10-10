package com.project.carrental.persistence.repository;

import com.project.carrental.persistence.model.CarEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, UUID>, JpaSpecificationExecutor<CarEntity> {
    List<CarEntity> findByBrandId(String brandId);
}
