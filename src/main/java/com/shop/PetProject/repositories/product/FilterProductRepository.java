package com.shop.PetProject.repositories.product;

import com.shop.PetProject.dtos.product.ProductFilter;
import com.shop.PetProject.models.ProductEntity;

import java.util.List;

public interface FilterProductRepository {
    List<ProductEntity> findAllByFilter(ProductFilter filter);
}
