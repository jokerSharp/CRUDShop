package com.shop.PetProject.repositories.order;

import com.shop.PetProject.models.OrderTotal;
import com.shop.PetProject.models.OrderTotalKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderTotalRepository extends JpaRepository<OrderTotal, OrderTotalKey> {
}
