package com.shop.PetProject.utils.converters.customers;

import com.shop.PetProject.dtos.CustomerDTO;
import com.shop.PetProject.dtos.OrderDTO;
import com.shop.PetProject.models.CustomerEntity;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerEntityToDTOConverter implements Converter<CustomerEntity, CustomerDTO> {
//    private final ConversionService conversionService;
//
//    public CustomerEntityToDTOConverter(ConversionService conversionService) {
//        this.conversionService = conversionService;
//    }

    @Override
    public CustomerDTO convert(CustomerEntity customerEntity) {
        return CustomerDTO.builder()
                .name(customerEntity.getName())
                .email(customerEntity.getEmail())
//                .orders(customerEntity.getOrders().stream()
//                        .map(order -> conversionService.convert(order, OrderDTO.class))
//                        .toList())
                .build();
    }
}
