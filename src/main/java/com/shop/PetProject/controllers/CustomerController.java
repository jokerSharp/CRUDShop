package com.shop.PetProject.controllers;

import com.shop.PetProject.dtos.CustomerDTO;
import com.shop.PetProject.services.CustomerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public CustomerDTO details(@PathVariable long id) {
        return customerService.getOne(id);
    }

    @PostMapping
    public void saveCustomer(@RequestBody CustomerDTO customerDTO) {
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
