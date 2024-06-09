package com.shop.PetProject.utils.converters.orders;

import com.shop.PetProject.dtos.order.OrderDTO;
import com.shop.PetProject.models.OrderEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

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
