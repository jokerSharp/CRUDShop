package com.shop.PetProject.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceAspect {

    @Around("Pointcuts.allMethods()")
    public Object addLoggingAround(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        long before = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long after = System.currentTimeMillis();
            System.out.println("The method %s execution time us %d milliseconds".formatted(methodSignature.getMethod().getName(), after - before));
            return result;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
