package com.shop.PetProject.testUtils.builders;

import com.shop.PetProject.dtos.customer.CustomerDTO;
import com.shop.PetProject.models.CustomerEntity;

import static com.shop.PetProject.testUtils.InputUtils.getRandomEmail;
import static com.shop.PetProject.testUtils.InputUtils.getRandomShortString;

public class CustomerBuilder {

    public static CustomerDTO getValidCustomerDTO() {
        return CustomerDTO.builder()
                .name(getRandomShortString())
                .email(getRandomEmail())
                .build();
    }

    public static CustomerEntity getValidCustomerEntity() {
        return CustomerEntity.builder()
                .name(getRandomShortString())
                .email(getRandomEmail())
                .build();
    }
}
