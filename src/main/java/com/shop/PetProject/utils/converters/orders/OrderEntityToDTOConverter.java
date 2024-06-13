package com.shop.PetProject.utils.converters.orders;

import com.shop.PetProject.dtos.order.OrderDTO;
import com.shop.PetProject.models.OrderEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderEntityToDTOConverter implements Converter<OrderEntity, OrderDTO> {

    @Override
    public OrderDTO convert(OrderEntity source) {
        return OrderDTO.builder()
                .id(source.getId())
                .customerId(source.getCustomer().getId())
                .status(source.getStatus())
                .totalPrice(source.getTotalPrice())
                .build();
    }
}
