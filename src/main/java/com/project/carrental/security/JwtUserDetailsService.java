package com.project.carrental.security;

import com.project.carrental.security.jwt.JwtUserFactory;
import com.project.carrental.service.UserService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userService.getByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found!");
        }

        var jwtUser = JwtUserFactory.create(user);

        log.info("IN loadByUserName - user with username: {} successfully loaded", username);

        return jwtUser;
    }
}
