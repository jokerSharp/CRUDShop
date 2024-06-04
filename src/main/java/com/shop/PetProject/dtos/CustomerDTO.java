package com.shop.PetProject.dtos;

import lombok.Builder;

import java.util.List;

@Builder
public record CustomerDTO(String name,
                          String email,
                          List<OrderDTO> orders) {
}
