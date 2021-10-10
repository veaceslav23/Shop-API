package com.project.carrental.service.model.request;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAllCarsRequest {

    private List<String> brandId;
    private List<String> model;
    private List<String> transmission;
    private List<String> fuelType;
    private List<String> traction;
    private Optional<Integer> minYear;
    private Optional<Integer> maxYear;
    private Optional<BigDecimal> minPrice;
    private Optional<BigDecimal> maxPrice;
}
