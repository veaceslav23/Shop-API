package com.project.carrental.rest.controller;

import com.project.carrental.facade.InvoiceFacade;
import com.project.carrental.service.model.InvoiceDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/invoices/")
public class InvoiceControllerApi {

    private final InvoiceFacade invoiceFacade;

    @PostMapping
    public ResponseEntity<InvoiceDto> createInvoice(@RequestBody InvoiceDto invoiceDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(invoiceFacade.createInvoice(invoiceDto));
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<InvoiceDto> getInvoiceById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok(invoiceFacade.getById(id));
    }

    @GetMapping(value = "user/{id}")
    public ResponseEntity<Page<InvoiceDto>> getInvoiceByUserId(
        @PathVariable(name = "id") UUID id, @RequestParam(name = "page",
        defaultValue = "0",
        required = false) Integer page,
        @RequestParam(name = "sort",
            defaultValue = "paymentAmount",
            required = false) String sort,
        @RequestParam(name = "limit",
            defaultValue = "10",
            required = false) Integer limit,
        @RequestParam(name = "direction",
            defaultValue = "ASC",
            required = false) String direction
    ) {
        return ResponseEntity.ok(invoiceFacade.getByUserId(id, page, limit, sort, direction));
    }
}
