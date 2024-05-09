package com.shop.PetProject.utils;

public class ProductAlreadyExistsException extends RuntimeException {

    public ProductAlreadyExistsException(String msg) {
        super(msg);
    }
}
