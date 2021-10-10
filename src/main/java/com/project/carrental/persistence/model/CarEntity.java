package com.project.carrental.persistence.model;

import javax.persistence.EntityListeners;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "cars")
public class CarEntity extends BaseEntity {
    @Id
    @Column(name = "id",
        columnDefinition = "VARCHAR(36) NOT NULL")
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID id;

    @Column
    private String model;

    @Column(name = "year_of_manufacture")
    private Integer yearOfManufacture;

    @Column
    private BigDecimal price;

    @Column(name = "is_reserved")
    private boolean isReserved;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "brand_id",
        referencedColumnName = "id"
    )
    private BrandEntity brand;

    @OneToMany(cascade = CascadeType.ALL,
        fetch = FetchType.EAGER)
    @JoinTable(name = "cars_image",
        joinColumns = @JoinColumn(name = "car_id"),
        inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Set<ImageEntity> image;

    @Column
    private String transmission;

    @Column(name = "fuel_type")
    private String fuelType;

    @Column(name = "engine_capacity")
    private Integer engineCapacity;

    @Column
    private String traction;
}
