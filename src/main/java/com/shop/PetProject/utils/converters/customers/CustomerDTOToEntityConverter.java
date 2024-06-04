package com.shop.PetProject.utils.converters.customers;


import com.shop.PetProject.dtos.CustomerDTO;
import com.shop.PetProject.models.CustomerEntity;
import com.shop.PetProject.models.OrderEntity;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerDTOToEntityConverter implements Converter<CustomerDTO, CustomerEntity> {
//    private final ConversionService conversionService;
//
//    public CustomerDTOToEntityConverter(ConversionService conversionService) {
//        this.conversionService = conversionService;
//    }

    @Override
    public CustomerEntity convert(CustomerDTO source) {
        return CustomerEntity.builder()
                .name(source.name())
                .email(source.email())
//                .orders(source.orders().stream()
//                        .map(orderDTO -> conversionService.convert(orderDTO, OrderEntity.class))
//                        .toList())
                .build();
    }
}
