package com.shop.PetProject.utils.builders;

import com.shop.PetProject.models.OrderEntity;
import com.shop.PetProject.models.OrderTotalKey;
import com.shop.PetProject.models.ProductEntity;

public class OrderTotalKeyBuilder {

    public static OrderTotalKey getOrderTotalKey(OrderEntity orderEntity, ProductEntity productEntity) {
        return OrderTotalKey.builder()
                .orderId(orderEntity.getId())
                .productId(productEntity.getId())
                .build();
    }
}
