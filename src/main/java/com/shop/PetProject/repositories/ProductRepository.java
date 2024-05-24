package com.shop.PetProject.repositories;

import com.shop.PetProject.dtos.ProductFilter;
import com.shop.PetProject.models.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID>, FilterProductRepository {

    long deleteByName(String name);

    List<ProductEntity> findByName(String name);

    List<ProductEntity> findByArticle(int article);

    @Modifying
    @Query(value = "update product p set p.price = p.price * 1.1",
            nativeQuery = true)
    void updatePrice();

//    List<ProductEntity> findAllByFilter(ProductFilter filter);
}
