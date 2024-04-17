package com.project.carrental.service.utils;

import com.project.carrental.persistence.model.BrandEntity;
import com.project.carrental.persistence.model.CarEntity;
import com.project.carrental.persistence.model.ImageEntity;
import com.project.carrental.persistence.model.InvoiceEntity;
import com.project.carrental.persistence.model.RevenueTypeEntity;
import com.project.carrental.persistence.model.UserEntity;
import com.project.carrental.persistence.model.enums.UserStatusEnum;
import com.project.carrental.service.model.AdminUserDto;
import com.project.carrental.service.model.BrandDto;
import com.project.carrental.service.model.CarDto;
import com.project.carrental.service.model.InvoiceDto;
import com.project.carrental.service.model.RevenueTypeDto;
import com.project.carrental.service.model.UserDto;
import com.project.carrental.service.model.request.BrandRequestDto;
import com.project.carrental.service.model.request.RegisterRequestDto;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.web.multipart.MultipartFile;

public class TransformersUtils {

    public static final Function<BrandEntity, BrandDto> convertToBrandDto =
        brand -> BrandDto.builder()
            .id(brand.getId())
            .code(brand.getName())
            .build();

    public static final Function<RevenueTypeEntity, RevenueTypeDto> convertToRevenueTypeDto =
        revenueTypeEntity -> RevenueTypeDto.builder()
            .id(revenueTypeEntity.getId())
            .code(revenueTypeEntity.getCode())
            .build();

    public static final Function<BrandDto, BrandEntity> convertToBrandEntity =
        brandDto -> BrandEntity.builder()
            .name(brandDto.getCode())
            .build();

    public static final Function<BrandRequestDto, BrandDto> convertFromBrandRequestToDto =
        brandRequestDto -> BrandDto.builder()
            .code(brandRequestDto.getName())
            .build();

    public static final Function<UserDto, UserEntity> convertFromUserDtoToUser =
        userDto -> UserEntity.builder()
            .firstName(userDto.getFirstName())
            .lastName(userDto.getLastName())
            .email(userDto.getEmail())
            .username(userDto.getUsername())
            .build();

    public static final Function<UserEntity, UserDto> convertFromUserToUserDto =
        user -> UserDto.builder()
            .id(user.getId())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .username(user.getUsername())
            //.imageCode(user.getImage().getCode())
            .build();

    public static final Function<AdminUserDto, UserEntity> convertFromAdminUserDtoToUser =
        userDto -> UserEntity.builder()
            .firstName(userDto.getFirstName())
            .lastName(userDto.getLastName())
            .email(userDto.getEmail())
            .username(userDto.getUsername())
            .status(UserStatusEnum.valueOf(userDto.getStatus()))
            .build();

    public static final Function<UserEntity, AdminUserDto> convertFromUserToAdminUserDto =
        user -> AdminUserDto.builder()
            .id(user.getId())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .username(user.getUsername())
            .status(user.getStatus().name())
            .build();

    public static final Function<RegisterRequestDto, UserEntity> convertFromRegisterRequestDtoToUser =
        request -> UserEntity.builder()
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .email(request.getEmail())
            .username(request.getUsername())
            .password(request.getPassword())
            .build();

    public static final Function<MultipartFile, ImageEntity> convertToImageEntity =
        imageDto -> ImageEntity.builder()
            .code(imageDto.getOriginalFilename())
            .build();

    public static final Function<CarEntity, CarDto> convertToCarDto =
        carEntity -> CarDto.builder()
            .id(carEntity.getId())
            .brand(carEntity.getBrand().getName())
            .yearOfManufacture(carEntity.getYearOfManufacture())
            .images(carEntity.getImage().stream().map(ImageEntity::getCode).collect(Collectors.toSet()))
            .isReserved(carEntity.isReserved())
            .price(carEntity.getPrice())
            .model(carEntity.getModel())
            .engineCapacity(carEntity.getEngineCapacity())
            .transmission(carEntity.getTransmission())
            .fuelType(carEntity.getFuelType())
            .traction(carEntity.getTraction())
            .build();

    public static final Function<InvoiceEntity, InvoiceDto> convertToInvoiceDto =
        invoiceEntity -> InvoiceDto.builder()
            .id(invoiceEntity.getId())
            .user(convertFromUserToUserDto.apply(invoiceEntity.getUser()))
            .car(convertToCarDto.apply(invoiceEntity.getCar()))
            .startDate(invoiceEntity.getStartDate().toString())
            .endDate(invoiceEntity.getEndDate().toString())
            .paymentAmount(invoiceEntity.getPaymentAmount())
            .revenueType(invoiceEntity.getRevenueType().getCode())
            .invoiceStatus(invoiceEntity.getInvoiceStatus())
            .pickupLocation(invoiceEntity.getPickupLocation())
            .dropoffLocation(invoiceEntity.getPickupLocation())
            .build();

}
