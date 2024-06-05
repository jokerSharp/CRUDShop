package com.shop.PetProject.services;

import com.shop.PetProject.dtos.OrderDTO;
import com.shop.PetProject.models.OrderEntity;
import com.shop.PetProject.repositories.CustomerRepository;
import com.shop.PetProject.repositories.OrderRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ConversionService conversionService;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, ConversionService conversionService) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.conversionService = conversionService;
    }

    public OrderDTO getOne(long id) {
        return conversionService.convert(orderRepository.findById(id), OrderDTO.class);
    }

    @Transactional
    public void save(OrderDTO orderDTO) {
//        Optional<CustomerEntity> customerEntity = customerRepository.findById(orderDTO.customerId());
        OrderEntity orderEntity = conversionService.convert(orderDTO, OrderEntity.class);
//        customerEntity.ifPresent(orderEntity::setCustomer);
        orderRepository.save(orderEntity);
    }

    public void update(long id, OrderDTO orderDTO) {
        Optional<OrderEntity> orderEntity = orderRepository.findById(id);
        if (orderEntity.isPresent()) {
            OrderEntity orderEntityToUpdate = conversionService.convert(orderDTO, OrderEntity.class);
            orderEntityToUpdate.setId(id);
            orderRepository.save(orderEntityToUpdate);
        } else {
            throw new RuntimeException("Order is not found");
        }
    }

    public void delete(long id) {
        Optional<OrderEntity> orderEntity = orderRepository.findById(id);
        if (orderEntity.isPresent()) {
            orderRepository.deleteById(id);
        } else {
            throw new RuntimeException("Order is not found");
        }
    }
}
