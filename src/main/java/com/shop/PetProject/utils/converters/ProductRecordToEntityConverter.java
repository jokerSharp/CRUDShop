package com.shop.PetProject.utils.converters;

import com.shop.PetProject.dtos.ProductDTO;
import com.shop.PetProject.models.ProductEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductRecordToEntityConverter implements Converter<ProductDTO, ProductEntity> {
    @Override
    public ProductEntity convert(ProductDTO source) {
        return ProductEntity.builder()
                .name(source.name())
                .price(source.price())
                .description(source.description())
                .category(source.category())
                .quantity(source.quantity())
                .article(source.article())
                .isAvailable(source.isAvailable())
                .build();
    }
}
