package com.shop.PetProject.utils.converters.orders;

import com.shop.PetProject.dtos.order.OrderTotalDTO;
import com.shop.PetProject.models.OrderTotal;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderTotalEntityToDTOConverter implements Converter<OrderTotal, OrderTotalDTO> {

    @Override
    public OrderTotalDTO convert(OrderTotal source) {
        return OrderTotalDTO.builder()

                .productName(source.getProduct().getName())
                .quantity(source.getQuantity())
                .build();
    }
}
