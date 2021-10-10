package com.project.carrental.rest.controller.admin;

import static com.project.carrental.service.utils.TransformersUtils.convertFromUserToAdminUserDto;

import com.project.carrental.facade.CarFacade;
import com.project.carrental.facade.InvoiceFacade;
import com.project.carrental.facade.UserFacade;
import com.project.carrental.service.UserService;
import com.project.carrental.service.model.AdminUserDto;
import com.project.carrental.service.model.CarDto;
import com.project.carrental.service.model.InvoiceDto;
import com.project.carrental.service.model.UserDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/admin/")
@RequiredArgsConstructor
public class AdminControllerApi {

    private final UserService userService;
    private final UserFacade userFacade;
    private final CarFacade carFacade;
    private final InvoiceFacade invoiceFacade;

    @GetMapping(value = "users/{id}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok(convertFromUserToAdminUserDto.apply(userService.getById(id)));
    }

    @GetMapping(value = "users/loyalest")
    public ResponseEntity<UserDto> getLoyalestUser() {
        return ResponseEntity.ok(userFacade.getMostLoyalUser());
    }

    @GetMapping(value = "users")
    public ResponseEntity<Page<AdminUserDto>> getAllUsers(
        @RequestParam(name = "page",
            defaultValue = "0",
            required = false) Integer page,
        @RequestParam(name = "sort",
            defaultValue = "username",
            required = false) String sort,
        @RequestParam(name = "limit",
            defaultValue = "10",
            required = false) Integer limit,
        @RequestParam(name = "direction",
            defaultValue = "ASC",
            required = false) String direction
    ) {
        return ResponseEntity.ok(userFacade.getAllUsers(page, limit, sort, direction));
    }

    @PostMapping(value = "/cars")
    public ResponseEntity<CarDto> addCar(
        @RequestBody CarDto carEntity
    ) {
        return ResponseEntity.ok(carFacade.create(carEntity));
    }

    @GetMapping(value = "invoices")
    public ResponseEntity<Page<InvoiceDto>> getInvoiceByUserId(
        @RequestParam(name = "page",
            defaultValue = "0",
            required = false) Integer page,
        @RequestParam(name = "sort",
            defaultValue = "paymentAmount",
            required = false) String sort,
        @RequestParam(name = "limit",
            defaultValue = "10",
            required = false) Integer limit,
        @RequestParam(name = "direction",
            defaultValue = "ASC",
            required = false) String direction
    ) {
        return ResponseEntity.ok(invoiceFacade.getAllInvoices(page, limit, sort, direction));
    }

    @PutMapping("invoices/paid/{id}")
    public ResponseEntity<UUID> moveToPaid(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(invoiceFacade.moveToPaid(id));
    }

    @DeleteMapping("invoices/{id}")
    public ResponseEntity<UUID> deleteInvoiceByID(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(invoiceFacade.deleteById(id));
    }
}
