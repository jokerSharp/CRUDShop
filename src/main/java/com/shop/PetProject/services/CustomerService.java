package com.shop.PetProject.services;

import com.querydsl.core.types.Predicate;
import com.shop.PetProject.dtos.customer.CustomerDTO;
import com.shop.PetProject.dtos.customer.CustomerFilter;
import com.shop.PetProject.dtos.QPredicates;
import com.shop.PetProject.exceptions.customer.CustomerNotFoundException;
import com.shop.PetProject.models.CustomerEntity;
import com.shop.PetProject.repositories.customer.CustomerRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.shop.PetProject.models.QCustomerEntity.customerEntity;

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

    public Page<CustomerDTO> getAll(CustomerFilter filter, Pageable pageable) {
        Predicate predicate = QPredicates.builder()
                .add(filter.name(), customerEntity.name::containsIgnoreCase)
                .add(filter.email(), customerEntity.email::containsIgnoreCase)
                .build();
        return customerRepository.findAll(predicate, pageable)
                .map(customer -> conversionService.convert(customer, CustomerDTO.class));
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
