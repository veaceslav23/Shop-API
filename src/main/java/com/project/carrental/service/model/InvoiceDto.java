package com.project.carrental.service.model;

import com.project.carrental.persistence.model.enums.InvoiceStatusEnum;
import com.project.carrental.persistence.model.RevenueTypeEntity;
import java.math.BigDecimal;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDto {

    private UUID id;

    @NotNull
    private UserDto user;

    @NotNull
    private CarDto car;

    @NotNull
    private String startDate;

    @NotNull
    private String endDate;

    @NotNull
    private BigDecimal paymentAmount;

    @NotNull
    private String revenueType;

    @NotNull
    private InvoiceStatusEnum invoiceStatus;

    @NotNull
    private String pickupLocation;

    @NotNull
    private String dropoffLocation;
}
