package com.project.carrental.facade;

import com.project.carrental.persistence.model.CarEntity;
import com.project.carrental.service.BrandService;
import com.project.carrental.service.CarService;
import com.project.carrental.service.ImageService;
import com.project.carrental.service.model.CarDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.val;

import static com.project.carrental.service.utils.Constants.CAR_PICTURES_PATH;
import static com.project.carrental.service.utils.TransformersUtils.convertToBrandEntity;
import static com.project.carrental.service.utils.TransformersUtils.convertToCarDto;
import static com.project.carrental.service.utils.TransformersUtils.convertToImageEntity;

@Component
@RequiredArgsConstructor
public class CarFacade {
    private final CarService carService;
    private final ImageService imageService;
    private final BrandService brandService;

    public CarDto create(CarDto car) {

        val images = car.getFiles()
            .stream()
            .map((image) -> imageService.uploadToLocalFileSystem(image, CAR_PICTURES_PATH))
            .collect(Collectors.toSet());

        val newCar = CarEntity.builder()
            .brand(brandService.getByBrandName(car.getBrand()))
            .model(car.getModel())
            .price(car.getPrice())
            .yearOfManufacture(car.getYearOfManufacture())
            .isReserved(false)
            .image(images)
            .build();

        return convertToCarDto.apply(carService.save(newCar));
    }

    public Page<CarDto> getAll(
        Integer page,
        Integer limit,
        String sort,
        String direction
    ) {
        val pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.fromString(direction), sort));

        return carService.getAll(pageable)
            .map(convertToCarDto);
    }
}
