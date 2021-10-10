package com.project.carrental.persistence.model;

import com.project.carrental.persistence.model.enums.InvoiceStatusEnum;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invoices")
public class InvoiceEntity extends BaseEntity {

    @Id
    @Column(name = "id",
        columnDefinition = "VARCHAR(36) NOT NULL")
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private CarEntity car;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "payment_amount")
    private BigDecimal paymentAmount;

    @ManyToOne
    @JoinColumn(name = "revenue_type", referencedColumnName = "id")
    private RevenueTypeEntity revenueType;

    @Enumerated(EnumType.STRING)
    @Column(name = "invoice_status")
    private InvoiceStatusEnum invoiceStatus;

    @Column(name = "pickup_location")
    private String pickupLocation;

    @Column(name = "dropoff_location")
    private String dropoffLocation;
}
