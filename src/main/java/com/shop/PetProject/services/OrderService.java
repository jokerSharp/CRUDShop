package com.shop.PetProject.services;

import com.shop.PetProject.dtos.OrderDTO;
import com.shop.PetProject.models.OrderEntity;
import com.shop.PetProject.repositories.OrderRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final ConversionService conversionService;

    public OrderService(OrderRepository orderRepository, ConversionService conversionService) {
        this.orderRepository = orderRepository;
        this.conversionService = conversionService;
    }

    public OrderDTO getOne(long id) {
        return conversionService.convert(orderRepository.findById(id), OrderDTO.class);
    }

    @Transactional
    public void save(OrderDTO order) {
        orderRepository.save(conversionService.convert(order, OrderEntity.class));
    }
}
