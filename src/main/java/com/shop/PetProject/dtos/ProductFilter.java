package com.shop.PetProject.dtos;

import com.shop.PetProject.models.Categories;

public record ProductFilter(String name,
                            Double price,
                            Integer quantity,
                            Boolean isAvailable) {
}
