package com.shop.PetProject.dtos.order;

import com.shop.PetProject.models.OrderStatuses;

import java.util.UUID;

public record OrderFilter(Long id,
                          OrderStatuses status,
                          UUID productId) {
}
