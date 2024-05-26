package com.shop.PetProject.utils.converters;

import com.shop.PetProject.controllers.responses.GetProductResponse;
import com.shop.PetProject.dtos.ProductDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductDTOToGetResponseConverter implements Converter<ProductDTO, GetProductResponse> {

    @Override
    public GetProductResponse convert(ProductDTO source) {
        return GetProductResponse.builder()
                .article(source.article())
                .name(source.name())
                .description(source.description())
                .category(source.category())
                .price(source.price())
                .quantity(source.quantity())
                .build();
    }
}
