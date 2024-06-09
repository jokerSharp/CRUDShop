package com.shop.PetProject.dtos.customer;

import com.shop.PetProject.dtos.order.OrderDTO;
import lombok.Builder;

import java.util.List;

@Builder
public record CustomerDTO(Long id,
                          String name,
                          String email,
                          List<OrderDTO> orders) {
}
