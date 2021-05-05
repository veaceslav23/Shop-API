package com.project.carrental.persistence.model;

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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cars")
public class CarEntity {
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
}
