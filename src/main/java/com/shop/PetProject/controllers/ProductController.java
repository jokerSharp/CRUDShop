package com.shop.PetProject.controllers;

import com.shop.PetProject.dtos.ProductDTO;
import com.shop.PetProject.models.ProductEntity;
import com.shop.PetProject.services.ProductService;
import com.shop.PetProject.utils.ProductAlreadyExistsException;
import com.shop.PetProject.utils.ProductErrorResponse;
import com.shop.PetProject.utils.ProductNotFoundException;
import com.shop.PetProject.utils.ProductValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ConversionService conversionService;
    private final ProductValidator productValidator;

    @GetMapping
    public List<ProductDTO> getAll() {
        return productService.getProduct().stream()
                .map(product -> conversionService.convert(product, ProductDTO.class))
                .toList();
    }


    @PostMapping
    public void save(@RequestBody @Valid ProductDTO productDTO, BindingResult bindingResult) {
        productValidator.validate(productDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            String errorMsg = errors.stream()
                    .map(error -> error.getField() + " - " + error.getDefaultMessage() + ";")
                    .collect(Collectors.joining());
            throw new ProductAlreadyExistsException(errorMsg);
        }
        productService.saveProduct(conversionService.convert(productDTO, ProductEntity.class));
    }

    @GetMapping("/{name}")
    public ProductDTO details(@PathVariable(name = "name") String name) {
        return conversionService.convert(productService.getProductByName(name), ProductDTO.class);
    }

    @PutMapping("/{name}")
    public void update(@PathVariable(name = "name") String name, @RequestBody ProductDTO productDTO) {
        productService.saveProduct(conversionService.convert(productDTO, ProductEntity.class));
    }

    @DeleteMapping("/{name}")
    public void deleteByName(@PathVariable(name = "name") String name) {
        productService.deleteProductByName(name);
    }

    @ExceptionHandler
    private ResponseEntity<ProductErrorResponse> handleException(ProductAlreadyExistsException e) {
        ProductErrorResponse response = new ProductErrorResponse(e.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ProductErrorResponse> handleException(ProductNotFoundException e) {
        ProductErrorResponse response = new ProductErrorResponse(e.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
