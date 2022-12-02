package com.project.carrental.service.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {

    private UUID id;

    @NotNull
    private String model;

    @NotNull
    private Integer yearOfManufacture;

    @NotNull
    private BigDecimal price;

    private boolean isReserved;

    @NotNull
    private String brand;

    private List<MultipartFile> files;

    private Set<String> images;

    @NotNull
    private String transmission;

    @NotNull
    private String fuelType;

    @NotNull
    private Integer engineCapacity;

    @NotNull
    private String traction;
}
