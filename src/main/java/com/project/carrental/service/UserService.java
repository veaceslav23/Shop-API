package com.project.carrental.service;

import com.project.carrental.persistence.model.UserEntity;
import com.project.carrental.persistence.model.enums.UserStatusEnum;
import com.project.carrental.persistence.repository.RoleRepository;
import com.project.carrental.persistence.repository.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import static java.util.Collections.singleton;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserEntity register(UserEntity user) {
        val roleUser = roleRepository.findByCode("USER");
        val userRoles = singleton(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(UserStatusEnum.ACTIVE);

        val registeredUser = userRepository.save(user);

        log.info("IN register -user: {} successfully registered", registeredUser);

        return registeredUser;
    }

    public List<UserEntity> getAll() {
        val users = userRepository.findAll();
        log.info("IN getAll - {} users found", users.size());
        return userRepository.findAll();
    }

    public UserEntity getByUsername(String username) {
        val user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));

        log.info("user : {} found by username: {}", user, user.getUsername());

        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserEntity getById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteById(UUID id) {
        userRepository.deleteById(id);
        log.info("User deleted.");
    }
}
