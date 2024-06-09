package com.shop.PetProject.repositories.product;

import com.shop.PetProject.models.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID>,
        FilterProductRepository,
        QuerydslPredicateExecutor<ProductEntity> {

    long deleteByName(String name);

    List<ProductEntity> findByName(String name);

    List<ProductEntity> findByArticle(int article);

    @Modifying
    @Query(value = "update product p set p.price = p.price * 1.1",
            nativeQuery = true)
    void updatePrice();
}
