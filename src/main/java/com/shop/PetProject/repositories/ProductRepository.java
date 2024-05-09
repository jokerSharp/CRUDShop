package com.shop.PetProject.repositories;

import com.shop.PetProject.models.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    long deleteByName(String name);

    List<ProductEntity> findByName(String name);
}
