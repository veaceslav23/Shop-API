package com.project.carrental.persistence.repository;

import com.project.carrental.persistence.model.RevenueTypeEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RevenueTypeRepository extends JpaRepository<RevenueTypeEntity, UUID> {

    Optional<RevenueTypeEntity> findByCode(String code);
}
