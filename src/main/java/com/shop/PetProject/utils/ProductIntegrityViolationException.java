package com.shop.PetProject.utils;

public class ProductIntegrityViolationException extends RuntimeException {

    public ProductIntegrityViolationException(String msg) {
        super(msg);
    }
}
