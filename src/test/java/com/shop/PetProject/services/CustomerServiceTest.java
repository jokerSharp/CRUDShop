package com.shop.PetProject.services;

import com.shop.PetProject.exceptions.customer.CustomerNotFoundException;
import com.shop.PetProject.models.CustomerEntity;
import com.shop.PetProject.repositories.customer.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.shop.PetProject.testUtils.builders.CustomerBuilder.getValidCustomerEntity;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void save_validCustomer_saveCustomer() {
        CustomerEntity validCustomerEntity = getValidCustomerEntity();
        customerRepository.save(validCustomerEntity);
        verify(customerRepository, times(1)).save(any(CustomerEntity.class));
    }

    @Test
    void getOne_noProduct_throwsException() {
        Assertions.assertThrows(CustomerNotFoundException.class, () -> customerService.getOne(1000));
    }
}
