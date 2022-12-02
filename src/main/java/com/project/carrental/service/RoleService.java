package com.project.carrental.service;

import com.project.carrental.persistence.model.RoleEntity;
import com.project.carrental.persistence.repository.RoleRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleEntity getByCode(String code) {
        return roleRepository.findByCode(code);
    }
}
