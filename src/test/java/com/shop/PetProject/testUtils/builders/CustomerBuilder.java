package com.shop.PetProject.testUtils.builders;

import com.shop.PetProject.dtos.customer.CustomerDTO;
import com.shop.PetProject.models.CustomerEntity;

import static com.shop.PetProject.testUtils.InputUtils.*;

public class CustomerBuilder {

    public static CustomerDTO getValidCustomerDTO() {
        return CustomerDTO.builder()
                .name(getRandomShortString())
                .email(getRandomEmail())
                .build();
    }

    public static CustomerDTO getInvalidCustomerDTO() {
        return CustomerDTO.builder()
                .email(getRandomEmail())
                .build();
    }

    public static CustomerEntity getValidCustomerEntity() {
        return CustomerEntity.builder()
                .id(Long.valueOf(getRandomPositiveInt()))
                .name(getRandomShortString())
                .email(getRandomEmail())
                .build();
    }

    public static CustomerEntity getInvalidCustomerEntity() {
        return CustomerEntity.builder()
                .email(getRandomEmail())
                .build();
    }
}
