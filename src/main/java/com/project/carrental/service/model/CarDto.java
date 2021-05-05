package com.project.carrental.service.model;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {

    private UUID id;

    private String model;

    private Integer yearOfManufacture;

    private BigDecimal price;

    private boolean isReserved;

    private String brand;

    private Set<MultipartFile> files;

    private Set<String> images;
}
