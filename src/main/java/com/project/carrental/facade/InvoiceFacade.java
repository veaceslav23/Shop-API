package com.project.carrental.facade;

import static com.project.carrental.service.utils.Constants.MAIL_SUBJECT;
import static com.project.carrental.service.utils.TransformersUtils.convertToInvoiceDto;

import com.project.carrental.persistence.model.InvoiceEntity;
import com.project.carrental.persistence.model.enums.InvoiceStatusEnum;
import com.project.carrental.service.BrandService;
import com.project.carrental.service.CarService;
import com.project.carrental.service.InvoiceService;
import com.project.carrental.service.RevenueTypeService;
import com.project.carrental.service.UserService;
import com.project.carrental.service.email.EmailServiceImpl;
import com.project.carrental.service.model.InvoiceDto;
import com.project.carrental.service.validation.ValidationService;
import java.time.LocalDate;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class InvoiceFacade {

    private final InvoiceService invoiceService;
    private final BrandService brandService;
    private final CarService carService;
    private final CarFacade carFacade;
    private final UserService userService;
    private final RevenueTypeService revenueTypeService;
    private final EmailServiceImpl emailService;
    private final ValidationService validationService;

    public InvoiceDto createInvoice(InvoiceDto invoiceDto) {

        var invoice = InvoiceEntity.builder()
            .invoiceStatus(InvoiceStatusEnum.NEW)
            .user(userService.getById(invoiceDto.getUser().getId()))
            .car(carService.getByID(invoiceDto.getCar().getId()))
            .startDate(LocalDate.parse(invoiceDto.getStartDate()))
            .endDate(LocalDate.parse(invoiceDto.getEndDate()))
            .paymentAmount(invoiceDto.getPaymentAmount())
            .revenueType(revenueTypeService.getByCode(invoiceDto.getRevenueType()))
            .pickupLocation(invoiceDto.getPickupLocation())
            .dropoffLocation(invoiceDto.getDropoffLocation())
            .build();

        validationService.validateInvoiceStartEndDate(invoice);

        carService.setIsReserved(invoice.getCar().toBuilder().isReserved(Boolean.TRUE).build());

        var createdInvoice = invoiceService.create(invoice);

        emailService.sendSimpleMessage(
            createdInvoice.getUser().getEmail(),
            MAIL_SUBJECT + createdInvoice.getId(),
            String.format(
                "Hello %s,\n\n We received successfully your order.\n\n "
                    + "Order details:\n"
                    + "Car: %s\n"
                    + "Start Date: %s\n"
                    + "End Date: %s\n"
                    + "Payment method: %s\n"
                    + "Total amount: %s\n",
                createdInvoice.getUser().getFirstName(),
                createdInvoice.getCar().getModel() + " "
                    + createdInvoice.getCar().getBrand().getName() + " "
                    + createdInvoice.getCar().getYearOfManufacture(),
                createdInvoice.getStartDate(),
                createdInvoice.getEndDate(),
                createdInvoice.getRevenueType().getCode(),
                createdInvoice.getPaymentAmount() + "EUR"
            )
        );
        log.info("sent email to {}", createdInvoice.getUser().getEmail());

        return convertToInvoiceDto.apply(createdInvoice);
    }

    public InvoiceDto getById(UUID id) {
        return convertToInvoiceDto.apply(invoiceService.getById(id));
    }

    public Page<InvoiceDto> getByUserId(UUID id, Integer page, Integer limit, String sort, String direction) {
        var pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.fromString(direction), sort));

        return invoiceService.getByUserId(id, pageable).map(convertToInvoiceDto);
    }

    public Page<InvoiceDto> getAllInvoices(
        Integer page,
        Integer limit,
        String sort,
        String direction
    ) {
        var pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.fromString(direction), sort));

        return invoiceService.getAllInvoices(pageable)
            .map(convertToInvoiceDto);
    }

    public UUID moveToPaid(UUID id) {
        var invoice = invoiceService.getById(id);
        invoice.setInvoiceStatus(InvoiceStatusEnum.PAID);

        return invoiceService.create(invoice).getId();
    }

    public UUID deleteById(UUID id) {
        var invoice = invoiceService.getById(id);
        var car = invoice.getCar().toBuilder()
            .isReserved(Boolean.FALSE)
            .build();

        carService.save(car);

        invoiceService.deleteById(id);

        return id;
    }
}
