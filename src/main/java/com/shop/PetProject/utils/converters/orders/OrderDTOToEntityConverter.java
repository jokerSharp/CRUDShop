package com.shop.PetProject.utils.converters.orders;

import com.shop.PetProject.dtos.order.OrderDTO;
import com.shop.PetProject.models.OrderEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderDTOToEntityConverter implements Converter<OrderDTO, OrderEntity> {

    @Override
    public OrderEntity convert(OrderDTO source) {
        return OrderEntity.builder()
                .status(source.status())
                .totalPrice(source.totalPrice())
                .build();
    }
}
