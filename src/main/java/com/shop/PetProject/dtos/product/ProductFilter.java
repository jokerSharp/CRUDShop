package com.shop.PetProject.dtos.product;

public record ProductFilter(String name,
                            Double price,
                            Integer quantity,
                            Boolean isAvailable) {
}
