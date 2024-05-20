package com.shop.PetProject.testUtils.converters;

import com.shop.PetProject.dtos.ProductDTO;
import com.shop.PetProject.models.ProductEntity;

public class ManualConverter {

    public static ProductDTO convert(ProductEntity source) {
        return ProductDTO.builder()
                .name(source.getName())
                .price(source.getPrice())
                .description(source.getDescription())
                .category(source.getCategory())
                .quantity(source.getQuantity())
                .article(source.getArticle())
                .build();
    }

    public static ProductEntity convert(ProductDTO source) {
        return ProductEntity.builder()
                .name(source.name())
                .price(source.price())
                .description(source.description())
                .category(source.category())
                .quantity(source.quantity())
                .article(source.article())
                .build();
    }
}
