package com.shop.PetProject.exceptions.product;

public class ProductIntegrityViolationException extends RuntimeException {

    public ProductIntegrityViolationException(String msg) {
        super(msg);
    }
}
