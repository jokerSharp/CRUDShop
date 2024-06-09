package com.shop.PetProject.utils.converters.customers;

import com.shop.PetProject.dtos.customer.CustomerDTO;
import com.shop.PetProject.models.CustomerEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerEntityToDTOConverter implements Converter<CustomerEntity, CustomerDTO> {

    @Override
    public CustomerDTO convert(CustomerEntity customerEntity) {
        return CustomerDTO.builder()
                .id(customerEntity.getId())
                .name(customerEntity.getName())
                .email(customerEntity.getEmail())
//                .orders(customerEntity.getOrders().stream()
//                        .map(order -> conversionService.convert(order, OrderDTO.class))
//                        .toList())
                .build();
    }
}
