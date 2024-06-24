package com.shop.PetProject.repositories.order;

import com.shop.PetProject.models.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>,
        FilterOrderRepository,
        QuerydslPredicateExecutor<OrderEntity> {

    @Query(value = """
            SELECT o.* FROM order_entity o
            JOIN order_total ot
            ON ot.order_id = o.id
            JOIN product p
            ON ot.product_id = p.id
            WHERE p.name = ?1
            """,
            nativeQuery = true)
    List<OrderEntity> findByProductName(String productName);
}
