package com.shop.PetProject.repositories.customer;

import com.shop.PetProject.dtos.customer.CustomerFilter;
import com.shop.PetProject.models.CustomerEntity;

import java.util.List;

public interface FilterCustomerRepository {

    List<CustomerEntity> findAllByFilter(CustomerFilter filter);
}
