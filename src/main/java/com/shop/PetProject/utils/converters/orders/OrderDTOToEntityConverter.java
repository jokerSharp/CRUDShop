package com.shop.PetProject.utils.converters.orders;

import com.shop.PetProject.dtos.OrderDTO;
import com.shop.PetProject.dtos.ProductDTO;
import com.shop.PetProject.models.OrderEntity;
import com.shop.PetProject.models.ProductEntity;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderDTOToEntityConverter implements Converter<OrderDTO, OrderEntity> {
//    private final ConversionService conversionService;
//
//    public OrderDTOToEntityConverter(ConversionService conversionService) {
//        this.conversionService = conversionService;
//    }

    @Override
    public OrderEntity convert(OrderDTO source) {
        return OrderEntity.builder()
//                .products(source.products().stream()
//                        .map(productDTO -> conversionService.convert(productDTO, ProductEntity.class))
//                        .collect(Collectors.toSet()))
                .status(source.status())
                .totalPrice(source.totalPrice())
                .build();
    }
}
