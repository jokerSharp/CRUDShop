package com.shop.PetProject.exceptions;

import com.shop.PetProject.models.ProductEntity;
import com.shop.PetProject.utils.ProductAlreadyExistsException;
import com.shop.PetProject.utils.ProductIntegrityViolationException;
import com.shop.PetProject.utils.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.shop.PetProject.exceptions.ExceptionUtils.getResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ProductAlreadyExistsException.class, })
    private ResponseEntity<Object> handleException(ProductAlreadyExistsException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
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

}
