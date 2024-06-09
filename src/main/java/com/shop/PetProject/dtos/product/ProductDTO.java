package com.shop.PetProject.dtos.product;

import com.shop.PetProject.models.Categories;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductDTO(String name,
                         BigDecimal price,
                         String description,
                         Categories category,
                         Integer quantity,
                         Integer article,
                         Boolean isAvailable) {
}


