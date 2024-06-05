package com.shop.PetProject.utils.converters.customers;


import com.shop.PetProject.dtos.CustomerDTO;
import com.shop.PetProject.models.CustomerEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerDTOToEntityConverter implements Converter<CustomerDTO, CustomerEntity> {

    @Override
    public CustomerEntity convert(CustomerDTO source) {
        return CustomerEntity.builder()
                .id(source.id())
                .name(source.name())
                .email(source.email())
//                .orders(source.orders().stream()
//                        .map(orderDTO -> conversionService.convert(orderDTO, OrderEntity.class))
//                        .toList())
                .build();
    }
}
