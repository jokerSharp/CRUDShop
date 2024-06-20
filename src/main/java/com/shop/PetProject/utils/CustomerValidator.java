package com.shop.PetProject.utils;

import com.shop.PetProject.dtos.customer.CustomerDTO;
import com.shop.PetProject.dtos.product.ProductDTO;
import com.shop.PetProject.exceptions.customer.CustomerEmailAlreadyExistsException;
import com.shop.PetProject.exceptions.product.ProductAlreadyExistsException;
import com.shop.PetProject.repositories.customer.CustomerRepository;
import com.shop.PetProject.repositories.product.ProductRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CustomerValidator implements Validator {

    private final CustomerRepository customerRepository;

    public CustomerValidator(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CustomerDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerDTO customerDTO = (CustomerDTO) target;
        if (customerDTO.email() == null) {
            errors.rejectValue("email", "", "Email should not be empty");
        } else if (customerRepository.findAll().stream()
                .anyMatch(entity -> entity.getEmail().equals(customerDTO.email()))) {
            throw new CustomerEmailAlreadyExistsException("Customer with this email already exists");
        }
        if (customerDTO.name() == null) {
            errors.rejectValue("name", "", "Name should not be empty");
        }
    }
}
