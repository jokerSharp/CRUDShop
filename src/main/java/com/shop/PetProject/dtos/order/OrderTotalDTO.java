package com.shop.PetProject.dtos.order;

import lombok.Builder;

@Builder
public record OrderTotalDTO(String productName,
                            Integer quantity) {
}
