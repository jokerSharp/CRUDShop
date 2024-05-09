package com.shop.PetProject.dtos;

import com.shop.PetProject.models.Categories;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private String name;
    private Double price;
    private String description;
    private Categories category;
    private Integer quantity;
    private Integer article;
}
