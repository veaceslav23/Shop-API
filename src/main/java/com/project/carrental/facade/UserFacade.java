package com.project.carrental.facade;

import static com.project.carrental.service.utils.Constants.DEFAULT_PROFILE_PICTURE;
import static com.project.carrental.service.utils.Constants.USER;
import static com.project.carrental.service.utils.Constants.USER_PROFILE_PICTURES_PATH;
import static com.project.carrental.service.utils.TransformersUtils.convertFromRegisterRequestDtoToUser;
import static com.project.carrental.service.utils.TransformersUtils.convertFromUserToAdminUserDto;
import static com.project.carrental.service.utils.TransformersUtils.convertFromUserToUserDto;
import static com.project.carrental.service.utils.TransformersUtils.convertToImageEntity;
import static java.util.Collections.singleton;
import static java.util.stream.Collectors.groupingBy;

import com.project.carrental.persistence.model.InvoiceEntity;
import com.project.carrental.persistence.model.enums.UserStatusEnum;
import com.project.carrental.security.jwt.JwtTokenProvider;
import com.project.carrental.service.ImageService;
import com.project.carrental.service.InvoiceService;
import com.project.carrental.service.RoleService;
import com.project.carrental.service.UserService;
import com.project.carrental.service.exception.ExceptionType;
import com.project.carrental.service.exception.GenericException;
import com.project.carrental.service.model.AdminUserDto;
import com.project.carrental.service.model.UserDto;
import com.project.carrental.service.model.request.AuthenticationRequestDto;
import com.project.carrental.service.model.request.LoginResponseDto;
import com.project.carrental.service.model.request.RegisterRequestDto;
import com.project.carrental.service.validation.ValidationService;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;
    private final ImageService imageService;
    private final InvoiceService invoiceService;
    private final RoleService roleService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final ValidationService validationService;

    public LoginResponseDto login(AuthenticationRequestDto authenticationRequest) {
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

        return LoginResponseDto.builder()
            .username(username)
            .token(token)
            .build();
    }

    public UserDto register(RegisterRequestDto requestDto) {
        val user = convertFromRegisterRequestDtoToUser.apply(requestDto);

        validationService.validateEmail(requestDto.getEmail());
        validationService.validateUsername(requestDto.getEmail());

        val roleUser = roleService.getByCode(USER);
        val userRoles = singleton(roleUser);
        val image = imageService.getImageById(DEFAULT_PROFILE_PICTURE);

        user.setPassword(userService.encodePassword(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(UserStatusEnum.ACTIVE);
        user.setImage(image);

        return convertFromUserToUserDto.apply(userService.register(user));
    }

    public UserDto update(
        UUID id,
        UserDto newUser
    ) {
        val foundUser = userService.getById(id);

        if (!newUser.getImage().isEmpty()) {
            imageService.uploadToLocalFileSystem(newUser.getImage(), USER_PROFILE_PICTURES_PATH);

            val image = convertToImageEntity.apply(newUser.getImage());

            foundUser.setImage(image);
        }
        foundUser.setEmail(newUser.getEmail());
        foundUser.setUsername(newUser.getUsername());
        foundUser.setFirstName(newUser.getFirstName());
        foundUser.setLastName(newUser.getLastName());

        return convertFromUserToUserDto.apply(userService.save(foundUser));
    }

    public Page<AdminUserDto> getAllUsers(
        Integer page,
        Integer limit,
        String sort,
        String direction
    ) {
        val pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.fromString(direction), sort));

        return userService.getAll(pageable)
            .map(convertFromUserToAdminUserDto);
    }

    public UserDto getMostLoyalUser() {
        val userInvoices = invoiceService.getAll()
            .stream()
            .collect(groupingBy(InvoiceEntity::getUser, Collectors.summingLong(invoice -> invoice.getPaymentAmount().longValue())));

        return userInvoices.entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(result -> convertFromUserToUserDto.apply(result.getKey()))
            .orElseThrow(() -> GenericException.of(ExceptionType.USER_NOT_FOUND));
    }
}
