package com.shop.PetProject.services;

import com.shop.PetProject.exceptions.order.OrderNotFoundException;
import com.shop.PetProject.repositories.order.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Test
    void getOne_noProduct_throwsException() {
        Assertions.assertThrows(OrderNotFoundException.class, () -> orderService.getOne(1));
    }
}
