package com.shop.PetProject.exceptions.customer;

public class CustomerEmailAlreadyExistsException extends RuntimeException {

    public CustomerEmailAlreadyExistsException(String message) {
        super(message);
    }
}
