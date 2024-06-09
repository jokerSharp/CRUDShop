package com.shop.PetProject.repositories.customer;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.shop.PetProject.dtos.customer.CustomerFilter;
import com.shop.PetProject.dtos.QPredicates;
import com.shop.PetProject.models.CustomerEntity;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.shop.PetProject.models.QCustomerEntity.customerEntity;

public class FilterCustomerRepositoryImpl implements FilterCustomerRepository {

    private final EntityManager em;

    public FilterCustomerRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<CustomerEntity> findAllByFilter(CustomerFilter filter) {
        Predicate predicate = QPredicates.builder()
                .add(filter.name(), customerEntity.name::containsIgnoreCase)
                .add(filter.email(), customerEntity.email::containsIgnoreCase)
                .build();

        return new JPAQuery<CustomerEntity>(em)
                .select(customerEntity)
                .from(customerEntity)
                .where(predicate)
                .fetch();
    }
}
