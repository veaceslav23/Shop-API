package com.project.carrental.rest.controller.user;

import com.project.carrental.facade.UserFacade;
import com.project.carrental.service.UserService;
import com.project.carrental.service.model.UserDto;

import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import lombok.RequiredArgsConstructor;

import static com.project.carrental.service.utils.TransformersUtils.convertFromUserToUserDto;
import static com.project.carrental.service.utils.TransformersUtils.convertToInvoiceDto;

@RestController
@RequestMapping(value = "/api/users/")
@RequiredArgsConstructor
public class UserControllerApi {
    private final UserService userService;
    private final UserFacade userFacade;

    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok(convertFromUserToUserDto.apply(userService.getById(id)));
    }

    @GetMapping(value = "username/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable(name = "username") String username) {
        val user = userService.getByUsername(username);

        val invoices = user.getInvoices()
            .stream()
            .map(convertToInvoiceDto)
            .collect(Collectors.toSet());

        val userDto = convertFromUserToUserDto.apply(user);
        userDto.setInvoices(invoices);

        return ResponseEntity.ok(userDto);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<UserDto> updateUserById(
        @PathVariable(name = "id") UUID id,
        @Valid @ModelAttribute UserDto userDto
    ) {
        return ResponseEntity.ok().body(userFacade.update(id, userDto));
    }
}
