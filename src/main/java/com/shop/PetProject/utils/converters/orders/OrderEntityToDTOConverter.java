package com.shop.PetProject.utils.converters.orders;

import com.shop.PetProject.dtos.order.OrderDTO;
import com.shop.PetProject.models.OrderEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderEntityToDTOConverter implements Converter<OrderEntity, OrderDTO> {
//    private final ConversionService conversionService;
//
//    public OrderEntityToDTOConverter(ConversionService conversionService) {
//        this.conversionService = conversionService;
//    }

    @Override
    public OrderDTO convert(OrderEntity source) {
        return OrderDTO.builder()
//                .products(source.getProducts().stream()
//                        .map(productEntity -> conversionService.convert(productEntity, ProductDTO.class))
//                        .collect(Collectors.toSet()))
                .id(source.getId())
                .customerId(source.getCustomer().getId())
                .status(source.getStatus())
                .totalPrice(source.getTotalPrice())
                .build();
    }
}
