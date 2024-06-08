package com.shop.PetProject.services;

import com.shop.PetProject.dtos.CustomerDTO;
import com.shop.PetProject.exceptions.customer.CustomerNotFoundException;
import com.shop.PetProject.models.CustomerEntity;
import com.shop.PetProject.repositories.CustomerRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ConversionService conversionService;

    public CustomerService(CustomerRepository customerRepository, ConversionService conversionService) {
        this.customerRepository = customerRepository;
        this.conversionService = conversionService;
    }

    public CustomerDTO getOne(long id) {
        return conversionService.convert(customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer is not found")), CustomerDTO.class);
    }

    @Transactional
    public void save(CustomerDTO customerDTO) {
        customerRepository.save(conversionService.convert(customerDTO, CustomerEntity.class));
    }

    @Transactional
    public void update(long id, CustomerDTO customerDTO) {
        Optional<CustomerEntity> customerEntity = customerRepository.findById(id);
        if (customerEntity.isPresent()) {
            CustomerEntity customerEntityToUpdate = conversionService.convert(customerDTO, CustomerEntity.class);
            customerEntityToUpdate.setId(id);
            customerRepository.save(customerEntityToUpdate);
        } else {
            throw new CustomerNotFoundException("Customer is not found");
        }
    }

    @Transactional
    public void delete(long id) {
        Optional<CustomerEntity> customerEntity = customerRepository.findById(id);
        if (customerEntity.isPresent()) {
            customerRepository.deleteById(id);
        } else {
            throw new CustomerNotFoundException("Customer is not found");
        }
    }
}
