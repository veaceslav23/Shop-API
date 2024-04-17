package com.project.carrental.service;

import static com.project.carrental.service.exception.ExceptionType.CAR_NOT_FOUND;

import com.project.carrental.persistence.model.BrandEntity;
import com.project.carrental.persistence.model.BrandEntity_;
import com.project.carrental.persistence.model.CarEntity;
import com.project.carrental.persistence.model.CarEntity_;
import com.project.carrental.persistence.repository.CarRepository;
import com.project.carrental.service.exception.GenericException;
import com.project.carrental.service.model.request.GetAllCarsRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final BrandService brandService;
    private final ImageService imageService;

    public static Specification<CarEntity> getCarsSpecification(GetAllCarsRequest request) {
        return isNotReserved()
            .and(brandFiltering(request.getBrandId()))
            .and(modelFiltering(request.getModel()))
            .and(transmissionFiltering(request.getTransmission()))
            .and(fuelTypeFiltering(request.getFuelType()))
            .and(tractionFiltering(request.getTraction()))
            .and(yearFiltering(request.getMinYear(), request.getMaxYear()))
            .and(priceFiltering(request.getMinPrice(), request.getMaxPrice()));
    }

    private static Specification<CarEntity> yearFiltering(Optional<Integer> minYear, Optional<Integer> maxYear) {

        return (root, query, builder) -> {
            if (minYear.isEmpty() && maxYear.isEmpty()) {
                return builder.conjunction();
            }
            if (minYear.isEmpty()) {
                return builder.lessThanOrEqualTo(root.get(CarEntity_.yearOfManufacture), maxYear.get());
            }
            if (maxYear.isEmpty()) {
                return builder.greaterThanOrEqualTo(root.get(CarEntity_.yearOfManufacture), minYear.get());
            }
            return builder.between(root.get(CarEntity_.yearOfManufacture), minYear.get(), maxYear.get());
        };
    }

    private static Specification<CarEntity> priceFiltering(Optional<BigDecimal> minPrice, Optional<BigDecimal> maxPrice) {

        return (root, query, builder) -> {
            if (minPrice.isEmpty() && maxPrice.isEmpty()) {
                return builder.conjunction();
            }
            if (minPrice.isEmpty()) {
                return builder.lessThanOrEqualTo(root.get(CarEntity_.price), maxPrice.get());
            }
            if (maxPrice.isEmpty()) {
                return builder.greaterThanOrEqualTo(root.get(CarEntity_.price), minPrice.get());
            }
            return builder.between(root.get(CarEntity_.price), minPrice.get(), maxPrice.get());
        };
    }

    private static Specification<CarEntity> brandFiltering(List<String> brandId) {

        return (root, query, builder) -> {
            if (brandId.isEmpty()) {
                return builder.conjunction();
            }
            return query.where(root.get(CarEntity_.brand).get(BrandEntity_.id).in(brandId)).getRestriction();
        };
    }

    private static Specification<CarEntity> modelFiltering(List<String> modelId) {

        return (root, query, builder) -> {
            if (modelId.isEmpty()) {
                return builder.conjunction();
            }
            return query.where(root.get(CarEntity_.brand).get(BrandEntity_.id).in(modelId)).getRestriction();
        };
    }

    private static Specification<CarEntity> yearFilter(List<String> brandId) {

        return (root, query, builder) -> {
            if (brandId.isEmpty()) {
                return builder.conjunction();
            }
            return query.where(root.get(CarEntity_.brand).get(BrandEntity_.id).in(brandId)).getRestriction();
        };
    }

    private static Specification<CarEntity> transmissionFiltering(List<String> transmission) {

        return (root, query, builder) -> {
            if (transmission.isEmpty()) {
                return builder.conjunction();
            }
            return query.where(root.get(CarEntity_.transmission).in(transmission)).getRestriction();
        };
    }

    private static Specification<CarEntity> fuelTypeFiltering(List<String> fuelType) {

        return (root, query, builder) -> {
            if (fuelType.isEmpty()) {
                return builder.conjunction();
            }
            return query.where(root.get(CarEntity_.fuelType).in(fuelType)).getRestriction();
        };
    }

    private static Specification<CarEntity> tractionFiltering(List<String> traction) {

        return (root, query, builder) -> {
            if (traction.isEmpty()) {
                return builder.conjunction();
            }
            return query.where(root.get(CarEntity_.traction).in(traction)).getRestriction();
        };
    }

    private static Specification<CarEntity> isNotReserved() {

        return (root, query, builder) -> {
            return query.where(builder.isFalse(root.get(CarEntity_.isReserved))).getRestriction();
        };
    }

    public Page<CarEntity> getAll(
        Specification<CarEntity> specification,
        Pageable pageable
    ) {
        return carRepository.findAll(specification, pageable);
    }

    public CarEntity getByID(UUID id) {
        return carRepository.findById(id).orElseThrow(() -> GenericException.of(CAR_NOT_FOUND));
    }

    public List<CarEntity> getByBrand(BrandEntity brandEntity) {
        return carRepository.findByBrandId(brandEntity.getId());
    }

    public CarEntity save(CarEntity car) {
        var newCar = carRepository.save(car);

        log.info("IN save car - car: {} successfully created", newCar);

        return newCar;
    }

    public void deleteById(UUID id) {
        carRepository.deleteById(id);
    }

    public void setIsReserved(CarEntity car) {
        carRepository.save(car);
    }
}
