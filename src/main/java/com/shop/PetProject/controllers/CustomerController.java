package com.shop.PetProject.controllers;

import com.shop.PetProject.dtos.PageResponse;
import com.shop.PetProject.dtos.customer.CustomerDTO;
import com.shop.PetProject.dtos.customer.CustomerFilter;
import com.shop.PetProject.exceptions.customer.CustomerEmailAlreadyExistsException;
import com.shop.PetProject.services.CustomerService;
import com.shop.PetProject.utils.CustomerValidator;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerValidator customerValidator;

    public CustomerController(CustomerService customerService, CustomerValidator customerValidator) {
        this.customerService = customerService;
        this.customerValidator = customerValidator;
    }

    @GetMapping
    public PageResponse<CustomerDTO> pageableSearch(CustomerFilter filter, Pageable pageable) {
        Page<CustomerDTO> page = customerService.getAll(filter, pageable);
        PageResponse<CustomerDTO> pageResponse = PageResponse.of(page);
        return pageResponse;
    }

    @GetMapping("/{id}")
    public CustomerDTO details(@PathVariable long id) {
        return customerService.getOne(id);
    }

    @PostMapping
    public void save(@RequestBody @Valid CustomerDTO customerDTO, BindingResult bindingResult) {
        customerValidator.validate(customerDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            String errorMsg = errors.stream()
                    .map(error -> error.getField() + " - " + error.getDefaultMessage() + ";")
                    .collect(Collectors.joining());
            throw new CustomerEmailAlreadyExistsException(errorMsg);
        }
        customerService.save(customerDTO);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable(name = "id") long id, @RequestBody CustomerDTO customerDTO) {
        customerService.update(id, customerDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") long id) {
        customerService.delete(id);
    }
}
