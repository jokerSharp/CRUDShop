package com.shop.PetProject.dtos.order;

import com.shop.PetProject.models.OrderStatuses;

public record OrderFilter(Long id,
                          OrderStatuses status) {
}
