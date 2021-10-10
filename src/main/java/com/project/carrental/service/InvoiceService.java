package com.project.carrental.service;

import com.project.carrental.persistence.model.InvoiceEntity;
import com.project.carrental.persistence.repository.InvoiceRepository;
import com.project.carrental.service.exception.ExceptionType;
import com.project.carrental.service.exception.GenericException;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceEntity create(InvoiceEntity invoiceEntity) {
        val invoice = invoiceRepository.save(invoiceEntity);

        log.info("in saveInvoice: created new invoice with ID : {} for user : {}", invoiceEntity.getId(), invoice.getUser().getUsername());

        return invoice;
    }

    public Page<InvoiceEntity> getAllInvoices(Pageable pageable) {
        val invoices = invoiceRepository.findAll(pageable);

        log.info("in getAllInvoices: found {} invoices", invoices.getTotalElements());

        return invoices;
    }

    public InvoiceEntity getById(UUID id) {
        return invoiceRepository.findById(id)
            .orElseThrow(() -> GenericException.of(ExceptionType.INVOICE_NOT_FOUND));
    }

    public Page<InvoiceEntity> getByUserId(UUID id, Pageable pageable) {
        return invoiceRepository.findByUserId(id, pageable);
    }

    public List<InvoiceEntity> getAll() {
        return invoiceRepository.findAll();
    }

    public void deleteById(UUID id) {
        invoiceRepository.deleteById(id);
        log.info("Invoice with id {} successfully deleted", id);
    }
}
