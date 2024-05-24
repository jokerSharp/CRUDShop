package com.shop.PetProject.dtos;

import com.shop.PetProject.models.Categories;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
public record ProductDTO(String name,
                         Double price,
                         String description,
                         Categories category,
                         Integer quantity,
                         Integer article,
                         Boolean isAvailable) {
}


