package com.project.carrental.facade;

import static com.project.carrental.service.CarService.getCarsSpecification;
import static com.project.carrental.service.exception.ExceptionType.BRAND_NOT_FOUND;
import static com.project.carrental.service.utils.Constants.CAR_PICTURES_PATH;
import static com.project.carrental.service.utils.TransformersUtils.convertToCarDto;

import com.project.carrental.persistence.model.CarEntity;
import com.project.carrental.persistence.model.ImageEntity;
import com.project.carrental.service.BrandService;
import com.project.carrental.service.CarService;
import com.project.carrental.service.ImageService;
import com.project.carrental.service.exception.GenericException;
import com.project.carrental.service.model.CarDto;
import com.project.carrental.service.model.request.GetAllCarsRequest;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CarFacade {

    private final CarService carService;
    private final ImageService imageService;
    private final BrandService brandService;

    public CarDto create(CarDto car) {

        var images = car.getFiles()
            .stream()
            .map(image -> imageService.uploadToLocalFileSystem(image, CAR_PICTURES_PATH))
            .collect(Collectors.toSet());

        var brand = brandService.getByUuid(car.getBrand())
            .orElseThrow(() -> GenericException.of(BRAND_NOT_FOUND));

        var newCar = CarEntity.builder()
            .brand(brand)
            .model(car.getModel())
            .price(car.getPrice())
            .yearOfManufacture(car.getYearOfManufacture())
            .traction(car.getTraction())
            .transmission(car.getTransmission())
            .fuelType(car.getFuelType())
            .engineCapacity(car.getEngineCapacity())
            .isReserved(false)
            .image(images)
            .build();

        return convertToCarDto.apply(carService.save(newCar));
    }

    public Page<CarDto> getAll(
        Integer page,
        Integer limit,
        String sort,
        String direction,
        GetAllCarsRequest request
    ) {
        var pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.fromString(direction), sort));
        var specification = getCarsSpecification(request);

        return carService.getAll(specification, pageable)
            .map(convertToCarDto);
    }

    public CarDto getById(UUID id) {
        return convertToCarDto.apply(carService.getByID(id));
    }

    public CarDto updateByID(UUID id, CarDto newCar) {
        var brand = brandService.getByUuid(newCar.getBrand())
            .orElseThrow(() -> GenericException.of(BRAND_NOT_FOUND));
        Set<ImageEntity> images = Collections.emptySet();
        try {
            var car = carService.getByID(id);

            if (newCar.getFiles() != null) {
                images = newCar.getFiles()
                    .stream()
                    .map(image -> imageService.uploadToLocalFileSystem(image, CAR_PICTURES_PATH))
                    .collect(Collectors.toSet());
                car.getImage().addAll(images);
            }

            return convertToCarDto.apply(carService.save(car.toBuilder()
                .brand(brand)
                .model(newCar.getModel())
                .fuelType(newCar.getFuelType())
                .transmission(newCar.getTransmission())
                .traction(newCar.getTraction())
                .price(newCar.getPrice())
                .isReserved(newCar.isReserved())
                .engineCapacity(newCar.getEngineCapacity())
                .yearOfManufacture(newCar.getYearOfManufacture())
                .image(car.getImage())
                .build()));

        } catch (GenericException e) {
            return convertToCarDto.apply(carService.save(CarEntity.builder()
                .brand(brand)
                .model(newCar.getModel())
                .fuelType(newCar.getFuelType())
                .transmission(newCar.getTransmission())
                .traction(newCar.getTraction())
                .price(newCar.getPrice())
                .isReserved(newCar.isReserved())
                .engineCapacity(newCar.getEngineCapacity())
                .yearOfManufacture(newCar.getYearOfManufacture())
                .image(images)
                .build()));
        }
    }

}
