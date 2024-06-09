package com.shop.PetProject.repositories.order;

import com.shop.PetProject.models.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>,
        FilterOrderRepository,
        QuerydslPredicateExecutor<OrderEntity> {
}
