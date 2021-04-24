package com.project.carrental.security.jwt;

import com.project.carrental.persistence.model.RoleEntity;
import com.project.carrental.persistence.model.UserEntity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.NoArgsConstructor;

import static com.project.carrental.persistence.model.enums.UserStatusEnum.ACTIVE;
import static java.util.stream.Collectors.toList;

@NoArgsConstructor
public final class JwtUserFactory {

    public static JwtUser create(UserEntity user) {
        return JwtUser.builder()
            .id(user.getId())
            .username(user.getUsername())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .password(user.getPassword())
            .isEnabled(user.getStatus().equals(ACTIVE))
            .lastPasswordResetDate(user.getUpdatedAt())
            .authorities(mapToGrantedAuthorities(new HashSet<>(user.getRoles())))
            .build();
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(Set<RoleEntity> userRoles) {
        return userRoles.stream()
            .map(role -> new SimpleGrantedAuthority(role.getCode()))
            .collect(toList());
    }
}
