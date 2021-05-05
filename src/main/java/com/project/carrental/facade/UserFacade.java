package com.project.carrental.facade;

import com.project.carrental.service.ImageService;
import com.project.carrental.service.UserService;
import com.project.carrental.service.model.UserDto;

import org.springframework.stereotype.Component;

import java.util.UUID;

import lombok.RequiredArgsConstructor;
import lombok.val;

import static com.project.carrental.service.utils.Constants.USER_PROFILE_PICTURES_PATH;
import static com.project.carrental.service.utils.TransformersUtils.convertFromUserToUserDto;
import static com.project.carrental.service.utils.TransformersUtils.convertToImageEntity;

@Component
@RequiredArgsConstructor
public class UserFacade {
    private final UserService userService;
    private final ImageService imageService;

    public UserDto update(
        UUID id,
        UserDto newUser
    ) {
        val foundUser = userService.getById(id);

        imageService.uploadToLocalFileSystem(newUser.getImage(), USER_PROFILE_PICTURES_PATH);

        val image = convertToImageEntity.apply(newUser.getImage());

        foundUser.setEmail(newUser.getEmail());
        foundUser.setUsername(newUser.getUsername());
        foundUser.setFirstName(newUser.getFirstName());
        foundUser.setLastName(newUser.getLastName());
        foundUser.setImage(image);

        return convertFromUserToUserDto.apply(userService.register(foundUser));
    }
}
