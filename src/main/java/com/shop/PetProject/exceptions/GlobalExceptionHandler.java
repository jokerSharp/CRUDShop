package com.shop.PetProject.exceptions;

import com.shop.PetProject.exceptions.customer.CustomerNotFoundException;
import com.shop.PetProject.exceptions.order.OrderNotFoundException;
import com.shop.PetProject.exceptions.product.ProductAlreadyExistsException;
import com.shop.PetProject.exceptions.product.ProductIntegrityViolationException;
import com.shop.PetProject.exceptions.product.ProductNotFoundException;
import com.shop.PetProject.models.CustomerEntity;
import com.shop.PetProject.models.OrderEntity;
import com.shop.PetProject.models.ProductEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.shop.PetProject.exceptions.ExceptionUtils.getResponseBody;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ProductAlreadyExistsException.class, })
    private ResponseEntity<Object> handleException(ProductAlreadyExistsException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(getResponseBody(e.getMessage(), ProductEntity.class));
    }

    @ExceptionHandler({ProductNotFoundException.class})
    private ResponseEntity<Object> handleException(ProductNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(getResponseBody(e.getMessage(), ProductEntity.class));
    }

    @ExceptionHandler({ProductIntegrityViolationException.class})
    private ResponseEntity<Object> handleException(ProductIntegrityViolationException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(getResponseBody(e.getMessage(), ProductEntity.class));
    }

    @ExceptionHandler({CustomerNotFoundException.class})
    private ResponseEntity<Object> handleException(CustomerNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(getResponseBody(e.getMessage(), CustomerEntity.class));
    }

    @ExceptionHandler({OrderNotFoundException.class})
    private ResponseEntity<Object> handleException(OrderNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(getResponseBody(e.getMessage(), OrderEntity.class));
    }

}
