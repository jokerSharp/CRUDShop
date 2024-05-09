package com.shop.PetProject.utils;

import com.shop.PetProject.dtos.ProductDTO;
import com.shop.PetProject.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductValidator implements Validator {

    private final ProductService productService;

    @Override
    public boolean supports(Class<?> clazz) {
        return ProductDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductDTO productDTO = (ProductDTO) target;
        ValidationUtils.rejectIfEmpty(errors, "name", "", "Name should not be empty");
        ValidationUtils.rejectIfEmpty(errors, "price", "", "Price should not be empty");
        ValidationUtils.rejectIfEmpty(errors, "category", "", "Category should not be empty");
        ValidationUtils.rejectIfEmpty(errors, "quantity", "", "Quantity should not be empty");
        ValidationUtils.rejectIfEmpty(errors, "article", "", "article should not be empty");
        if (productService.getProductByName(productDTO.getName()) != null) {
            errors.rejectValue("name", "", "Product with this name already exists");
        }
    }
}
