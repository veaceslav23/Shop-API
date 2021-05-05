package com.project.carrental.service;

import com.project.carrental.persistence.model.BrandEntity;
import com.project.carrental.persistence.model.CarEntity;
import com.project.carrental.persistence.repository.CarRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final BrandService brandService;
    private final ImageService imageService;

    public Page<CarEntity> getAll(Pageable pageable) {
        return carRepository.findAll(pageable);
    }

    public CarEntity getByID(UUID id) {
        return carRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NO_CONTENT));
    }

    public List<CarEntity> getByBrand(BrandEntity brandEntity) {
        return carRepository.findByBrand(brandEntity.getId());
    }

    public CarEntity save(CarEntity car) {
        val newCar = carRepository.save(car);

        log.info("IN save car - car: {} successfully created", newCar);

        return newCar;
    }

    public void deleteById(UUID id) {
        carRepository.deleteById(id);
    }
}
