package com.shop.PetProject.repositories;

import com.shop.PetProject.models.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<ProductEntity, Long> {
}
