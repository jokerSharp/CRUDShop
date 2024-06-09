package com.shop.PetProject.controllers;

import com.shop.PetProject.dtos.customer.CustomerDTO;
import com.shop.PetProject.dtos.customer.CustomerFilter;
import com.shop.PetProject.dtos.PageResponse;
import com.shop.PetProject.services.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
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
    public void save(@RequestBody CustomerDTO customerDTO) {
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
