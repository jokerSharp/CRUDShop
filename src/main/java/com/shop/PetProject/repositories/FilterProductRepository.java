package com.shop.PetProject.repositories;

import com.shop.PetProject.dtos.ProductFilter;
import com.shop.PetProject.models.ProductEntity;

import java.util.List;

public interface FilterProductRepository {
    List<ProductEntity> findAllByFilter(ProductFilter filter);
}
