package com.shop.PetProject.controllers.responses;

import com.shop.PetProject.models.Categories;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class GetProductResponse {

    private Integer article;

    private String name;

    private String description;

    private Categories category;

    private BigDecimal price;

    private Integer quantity;

}
