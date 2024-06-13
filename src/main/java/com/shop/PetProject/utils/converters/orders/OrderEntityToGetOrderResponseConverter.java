package com.shop.PetProject.utils.converters.orders;

import com.shop.PetProject.controllers.responses.GetOrderResponse;
import com.shop.PetProject.models.OrderEntity;
import org.springframework.core.convert.converter.Converter;

public class OrderEntityToGetOrderResponseConverter implements Converter<OrderEntity, GetOrderResponse> {

    @Override
    public GetOrderResponse convert(OrderEntity source) {
        return GetOrderResponse.builder()
                .orderId(source.getId())
                .customerName(source.getCustomer().getName())
                .totalPrice(source.getTotalPrice())
                .status(source.getStatus())
                .build();
    }
}
