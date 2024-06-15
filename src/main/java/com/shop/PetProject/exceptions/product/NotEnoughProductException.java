package com.shop.PetProject.exceptions.product;

public class NotEnoughProductException extends RuntimeException {

    public NotEnoughProductException(String msg) {
        super(msg);
    }
}
