package com.project.carrental.rest.controller;

import com.project.carrental.security.jwt.JwtTokenProvider;
import com.project.carrental.service.UserService;
import com.project.carrental.service.model.AuthenticationRequestDto;
import com.project.carrental.service.model.LoginResponseDto;
import com.project.carrental.service.model.RegisterRequestDto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.val;

import static com.project.carrental.service.utils.TransformersUtils.convertFromRegisterRequestDtoToUser;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth/")
public class AuthenticationControllerApi {

//    @Value("${upload.path}")
//    private final String uploadPath;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @PostMapping(
        value = "login",
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity login(@RequestBody AuthenticationRequestDto authenticationRequest) {
        try {
            val username = authenticationRequest.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                username,
                authenticationRequest.getPassword()
            ));
            val user = userService.getByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            val token = jwtTokenProvider.createToken(username, user.getRoles());

            val response = LoginResponseDto.builder()
                .username(username)
                .token(token)
                .build();

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping(
        value = "register",
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity register(
        @RequestBody RegisterRequestDto registerRequestDto
    ) {
        return ResponseEntity.ok(userService.register(
            convertFromRegisterRequestDtoToUser.apply(registerRequestDto)
        ));
    }

//    @PostMapping(value = "register",
//        consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity register(
//        @RequestBody RegisterRequestDto registerRequestDto,
//        @RequestParam("file")
//            MultipartFile file
//    ) throws IOException {
//
//        val newUser = convertFromRegisterRequestDtoToUser.apply(registerRequestDto);
//        if (file != null) {
//            val uploadDir = new File(uploadPath);
//            if (!uploadDir.exists()) {
//                uploadDir.mkdir();
//            }
//            val resultFileName = newUser.getUsername().concat("_profile." + file.getOriginalFilename());
//
//            file.transferTo(new File(uploadPath + resultFileName));
//
//            newUser.setProfilePicture(resultFileName);
//        }
//        return ResponseEntity.ok(userService.register(newUser));
//    }
}
