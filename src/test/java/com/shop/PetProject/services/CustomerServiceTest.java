package com.shop.PetProject.services;

import com.shop.PetProject.dtos.customer.CustomerDTO;
import com.shop.PetProject.dtos.product.ProductDTO;
import com.shop.PetProject.exceptions.customer.CustomerEmailAlreadyExistsException;
import com.shop.PetProject.exceptions.customer.CustomerNotFoundException;
import com.shop.PetProject.models.CustomerEntity;
import com.shop.PetProject.models.ProductEntity;
import com.shop.PetProject.repositories.customer.CustomerRepository;
import com.shop.PetProject.testUtils.converters.ManualConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import java.util.List;
import java.util.Optional;

import static com.shop.PetProject.testUtils.builders.CustomerBuilder.*;
import static com.shop.PetProject.testUtils.builders.ProductBuilder.getProductDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ConversionService conversionService;

    @Test
    void save_validCustomer_saveCustomer() {
        CustomerEntity validCustomerEntity = getValidCustomerEntity();
        customerRepository.save(validCustomerEntity);
        verify(customerRepository, times(1)).save(any(CustomerEntity.class));
    }

    @Test
    void save_invalidCustomer_saveCustomer() {
        CustomerEntity invalidCustomerEntity = getInvalidCustomerEntity();
        CustomerDTO invalidCustomerDTO = getInvalidCustomerDTO();
        when(conversionService.convert(invalidCustomerDTO, CustomerEntity.class)).thenReturn(invalidCustomerEntity);
        Assertions.assertThrows(CustomerEmailAlreadyExistsException.class, () -> customerService.save(invalidCustomerDTO));
    }

    @Test
    void getOne_customerExists_getCustomer() {
        CustomerEntity validCustomerEntity = getValidCustomerEntity();
        Mockito.when(customerRepository.findById(validCustomerEntity.getId())).thenReturn(Optional.of(validCustomerEntity));
        customerService.getOne(validCustomerEntity.getId());
        verify(customerRepository, times(1)).findById(anyLong());
    }

    @Test
    void getOne_noCustomer_throwsException() {
        Assertions.assertThrows(CustomerNotFoundException.class, () -> customerService.getOne(1000));
    }
}
