package com.shop.PetProject.repositories.order;

import com.shop.PetProject.dtos.order.OrderFilter;
import com.shop.PetProject.models.OrderEntity;

import java.util.List;

public interface FilterOrderRepository {

    List<OrderEntity> findAllByFilter(OrderFilter filter);
}
