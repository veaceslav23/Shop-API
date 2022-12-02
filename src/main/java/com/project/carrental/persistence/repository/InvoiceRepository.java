package com.project.carrental.persistence.repository;

import com.project.carrental.persistence.model.InvoiceEntity;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, UUID> {

    Page<InvoiceEntity> findByUserId(UUID id, Pageable pageable);
}
