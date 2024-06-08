package com.shop.PetProject.exceptions.product;

public class ProductAlreadyExistsException extends RuntimeException {

    public ProductAlreadyExistsException(String msg) {
        super(msg);
    }
}
