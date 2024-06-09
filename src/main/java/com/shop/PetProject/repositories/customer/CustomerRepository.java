package com.shop.PetProject.repositories.customer;

import com.shop.PetProject.models.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>,
        FilterCustomerRepository,
        QuerydslPredicateExecutor<CustomerEntity> {
}
