package com.shop.PetProject.repositories.order;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.shop.PetProject.dtos.QPredicates;
import com.shop.PetProject.dtos.order.OrderFilter;
import com.shop.PetProject.models.OrderEntity;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.shop.PetProject.models.QOrderEntity.orderEntity;

public class FilterOrderRepositoryImpl implements FilterOrderRepository{

    private final EntityManager em;

    public FilterOrderRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<OrderEntity> findAllByFilter(OrderFilter filter) {
        Predicate predicate = QPredicates.builder()
                .add(filter.id(), orderEntity.id::eq)
                .add(filter.status(), orderEntity.status::eq)
                .build();

        return new JPAQuery<OrderEntity>(em)
                .select(orderEntity)
                .from(orderEntity)
                .where(predicate)
                .fetch();
    }
}
