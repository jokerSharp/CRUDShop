package com.shop.PetProject.dtos;

import lombok.Builder;

import java.util.List;

@Builder
public record CustomerDTO(Long id,
                          String name,
                          String email,
                          List<OrderDTO> orders) {
}
