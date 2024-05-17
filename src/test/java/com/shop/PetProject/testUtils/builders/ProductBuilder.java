package com.shop.PetProject.testUtils.builders;

import com.shop.PetProject.dtos.ProductDTO;
import com.shop.PetProject.models.Categories;
import com.shop.PetProject.models.ProductEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class ProductBuilder {

    public static ProductEntity getProductEntity() {
        return ProductEntity.builder()
                .id(UUID.randomUUID())
                .name("apple")
                .price(1.5)
                .description("usual apple")
                .category(Categories.GROCERIES)
                .quantity(11100)
                .article(542267)
                .creationDate(LocalDateTime.now())
                .quantityChange(LocalDateTime.now())
                .build();
    }

    public static ProductDTO getProductDTO() {
        return ProductDTO.builder()
                .name("laptop")
                .price(1500.5)
                .description("usual laptop")
                .category(Categories.ELECTRONICS)
                .quantity(111)
                .article(1230987)
                .build();
    }
}