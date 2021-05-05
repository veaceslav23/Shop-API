package com.project.carrental.service.utils;

import com.project.carrental.persistence.model.BrandEntity;
import com.project.carrental.persistence.model.CarEntity;
import com.project.carrental.persistence.model.ImageEntity;
import com.project.carrental.persistence.model.UserEntity;
import com.project.carrental.persistence.model.enums.UserStatusEnum;
import com.project.carrental.service.model.AdminUserDto;
import com.project.carrental.service.model.BrandDto;
import com.project.carrental.service.model.BrandRequestDto;
import com.project.carrental.service.model.CarDto;
import com.project.carrental.service.model.RegisterRequestDto;
import com.project.carrental.service.model.UserDto;

import org.springframework.web.multipart.MultipartFile;

import java.util.function.Function;
import java.util.stream.Collectors;

public class TransformersUtils {
    public static final Function<BrandEntity, BrandDto> convertToBrandDto =
        brand -> BrandDto.builder()
            .id(brand.getId())
            .name(brand.getName())
            .build();

    public static final Function<BrandDto, BrandEntity> convertToBrandEntity =
        brandDto -> BrandEntity.builder()
        .name(brandDto.getName())
        .build();

    public static final Function<BrandRequestDto, BrandDto> convertFromBrandRequestToDto =
        brandRequestDto -> BrandDto.builder()
            .name(brandRequestDto.getName())
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
            .model(carEntity.getModel())
            .build();
}
