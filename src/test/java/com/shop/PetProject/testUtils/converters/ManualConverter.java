package com.shop.PetProject.testUtils.converters;

import com.shop.PetProject.dtos.customer.CustomerDTO;
import com.shop.PetProject.dtos.product.ProductDTO;
import com.shop.PetProject.models.CustomerEntity;
import com.shop.PetProject.models.ProductEntity;

import java.math.BigDecimal;

public class ManualConverter {

    public static ProductDTO convert(ProductEntity source) {
        return ProductDTO.builder()
                .name(source.getName())
                .price(BigDecimal.valueOf(source.getPrice()))
                .description(source.getDescription())
                .category(source.getCategory())
                .quantity(source.getQuantity())
                .article(source.getArticle())
                .build();
    }

    public static ProductEntity convert(ProductDTO source) {
        return ProductEntity.builder()
                .name(source.name())
                .price(source.price().doubleValue())
                .description(source.description())
                .category(source.category())
                .quantity(source.quantity())
                .article(source.article())
                .build();
    }

    public static CustomerEntity convert(CustomerDTO source) {
        return CustomerEntity.builder()
                .name(source.name())
                .email(source.email())
                .build();
    }
}
