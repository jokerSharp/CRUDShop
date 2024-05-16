package com.shop.PetProject.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* com.shop.PetProject.services.ProductService.*(..))")
    public void allMethods() {}
}
