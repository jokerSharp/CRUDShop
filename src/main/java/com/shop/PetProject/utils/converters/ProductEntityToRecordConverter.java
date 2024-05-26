package com.shop.PetProject.utils.converters;

import com.shop.PetProject.dtos.ProductDTO;
import com.shop.PetProject.models.ProductEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductEntityToRecordConverter implements Converter<ProductEntity, ProductDTO> {
    @Override
    public ProductDTO convert(ProductEntity source) {
        return ProductDTO.builder()
                .name(source.getName())
                .price(BigDecimal.valueOf(source.getPrice()))
                .description(source.getDescription())
                .category(source.getCategory())
                .quantity(source.getQuantity())
                .article(source.getArticle())
                .isAvailable(source.getIsAvailable())
                .build();
    }
}
