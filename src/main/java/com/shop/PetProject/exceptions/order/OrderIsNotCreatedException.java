package com.shop.PetProject.exceptions.order;

public class OrderIsNotCreatedException extends RuntimeException {

    public OrderIsNotCreatedException(String msg) {
        super(msg);
    }
}
