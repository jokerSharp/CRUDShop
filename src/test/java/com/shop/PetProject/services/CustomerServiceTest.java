package com.shop.PetProject.services;

import com.shop.PetProject.exceptions.customer.CustomerNotFoundException;
import com.shop.PetProject.repositories.customer.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void getOne_noProduct_throwsException() {
        Assertions.assertThrows(CustomerNotFoundException.class, () -> customerService.getOne(1000));
    }
}
