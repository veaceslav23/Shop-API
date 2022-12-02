package com.project.carrental.service;

import static com.project.carrental.service.exception.ExceptionType.REVENUE_TYPE_NOT_FOUND;
import static com.project.carrental.service.utils.TransformersUtils.convertToRevenueTypeDto;

import com.project.carrental.persistence.model.RevenueTypeEntity;
import com.project.carrental.persistence.repository.RevenueTypeRepository;
import com.project.carrental.service.exception.GenericException;
import com.project.carrental.service.model.RevenueTypeDto;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RevenueTypeService {

    private final RevenueTypeRepository revenueTypeRepository;

    public List<RevenueTypeDto> getAll() {
        return revenueTypeRepository.findAll()
            .stream()
            .map(convertToRevenueTypeDto)
            .collect(Collectors.toList());
    }

    public RevenueTypeEntity getByCode(String code) {
        return revenueTypeRepository.findByCode(code).orElseThrow(() -> GenericException.of(REVENUE_TYPE_NOT_FOUND));
    }
}
