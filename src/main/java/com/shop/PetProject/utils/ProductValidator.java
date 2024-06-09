package com.shop.PetProject.utils;

import com.shop.PetProject.dtos.product.ProductDTO;
import com.shop.PetProject.exceptions.product.ProductAlreadyExistsException;
import com.shop.PetProject.repositories.product.ProductRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProductValidator implements Validator {

    private final ProductRepository productRepository;

    public ProductValidator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ProductDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductDTO productDTO = (ProductDTO) target;
        if (productDTO.name() == null) {
            errors.rejectValue("name", "", "Name should not be empty");
        } else if (productRepository.findByName(productDTO.name()).stream()
                .findFirst().isPresent()) {
            throw new ProductAlreadyExistsException("Product with this name already exists");
        }
        if (productDTO.article() == null) {
            errors.rejectValue("article", "", "Article should not be empty");
        } else if (productRepository.findByArticle(productDTO.article()).stream()
                .findFirst().isPresent()) {
            throw new ProductAlreadyExistsException("Product with this article already exists");
        }
        if (productDTO.price() == null) {
            errors.rejectValue("price", "", "Price should not be empty");
        }
        if (productDTO.category() == null) {
            errors.rejectValue("category", "", "Category should not be empty");
        }
        if (productDTO.quantity() == null) {
            errors.rejectValue("quantity", "", "Quantity should not be empty");
        }
    }
}
