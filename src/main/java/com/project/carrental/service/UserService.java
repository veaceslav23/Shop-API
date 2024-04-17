package com.project.carrental.service;

import static com.project.carrental.service.exception.ExceptionType.USER_NOT_FOUND;

import com.project.carrental.persistence.model.UserEntity;
import com.project.carrental.persistence.repository.RoleRepository;
import com.project.carrental.persistence.repository.UserRepository;
import com.project.carrental.service.exception.GenericException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ImageService imageService;

    public UserEntity register(UserEntity user) {
        var registeredUser = userRepository.save(user);
        log.info("IN register -user: {} successfully registered", registeredUser);
        return registeredUser;
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public Page<UserEntity> getAll(Pageable pageable) {
        var users = userRepository.findAll(pageable);
        log.info("IN getAll - {} users found", users.getTotalElements());
        return users;
    }

    public UserEntity getByUsername(String username) {
        var user = userRepository.findByUsername(username)
            .orElseThrow(() -> GenericException.of(USER_NOT_FOUND));

        log.info("user : {} found by username: {}", user, user.getUsername());

        return user;
    }

    public UserEntity getById(UUID id) {
        return userRepository.findById(id)
            .orElseThrow(() -> GenericException.of(USER_NOT_FOUND));
    }

    public void deleteById(UUID id) {
        userRepository.deleteById(id);
        log.info("User deleted.");
    }

    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }
}
