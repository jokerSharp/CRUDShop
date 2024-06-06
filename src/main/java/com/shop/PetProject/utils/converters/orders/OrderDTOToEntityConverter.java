package com.shop.PetProject.utils.converters.orders;

import com.shop.PetProject.dtos.OrderDTO;
import com.shop.PetProject.models.OrderEntity;
import com.shop.PetProject.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class OrderDTOToEntityConverter implements Converter<OrderDTO, OrderEntity> {

//    private CustomerRepository customerRepository;
//
//    public OrderDTOToEntityConverter(CustomerRepository customerRepository) {
//        this.customerRepository = customerRepository;
//    }

    @Override
    public OrderEntity convert(OrderDTO source) {
        return OrderEntity.builder()
//                .customer(customerRepository.findById(source.customerId()).get())
                .status(source.status())
                .totalPrice(source.totalPrice())
                .build();
    }

//    @Autowired
//    public void setCustomerRepository(CustomerRepository customerRepository) {
//        this.customerRepository = customerRepository;
//    }
}
