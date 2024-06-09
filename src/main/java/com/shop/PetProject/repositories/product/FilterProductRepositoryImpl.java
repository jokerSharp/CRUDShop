package com.shop.PetProject.repositories.product;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.shop.PetProject.dtos.product.ProductFilter;
import com.shop.PetProject.dtos.QPredicates;
import com.shop.PetProject.models.ProductEntity;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.shop.PetProject.models.QProductEntity.productEntity;

public class FilterProductRepositoryImpl implements FilterProductRepository {
    private final EntityManager entityManager;

    public FilterProductRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<ProductEntity> findAllByFilter(ProductFilter filter) {
        Predicate predicate = QPredicates.builder()
                .add(filter.name(), productEntity.name::containsIgnoreCase)
                .add(filter.quantity(), productEntity.quantity::loe)
                .add(filter.price(), productEntity.price::goe)
                .add(filter.isAvailable(), productEntity.isAvailable::eq)
                .build();

        return new JPAQuery<ProductEntity>(entityManager)
                .select(productEntity)
                .from(productEntity)
                .where(predicate)
                .fetch();
    }
}
