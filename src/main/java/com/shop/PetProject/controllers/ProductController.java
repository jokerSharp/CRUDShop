package com.shop.PetProject.controllers;

import com.shop.PetProject.dtos.ProductDTO;
import com.shop.PetProject.services.ProductService;
import com.shop.PetProject.utils.ProductAlreadyExistsException;
import com.shop.PetProject.utils.ProductNotFoundException;
import com.shop.PetProject.utils.ProductValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    private final ProductValidator productValidator;

    @GetMapping()
    public ResponseEntity<List<ProductDTO>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<ProductDTO> productPage = productService.getProducts(page, size);
        List<ProductDTO> products = productPage.getContent();
        return ResponseEntity.ok(products);
    }


    @PostMapping
    public void save(@RequestBody @Valid ProductDTO productDTO, BindingResult bindingResult) {
//        productValidator.validate(productDTO, bindingResult);
//        if (bindingResult.hasErrors()) {
//            List<FieldError> errors = bindingResult.getFieldErrors();
//            String errorMsg = errors.stream()
//                    .map(error -> error.getField() + " - " + error.getDefaultMessage() + ";")
//                    .collect(Collectors.joining());
//            throw new ProductAlreadyExistsException(errorMsg);
//        }
        productService.saveProduct(productDTO);
    }

    @GetMapping("/{name}")
    public ProductDTO details(@PathVariable(name = "name") String name) {
        ProductDTO productDTO = productService.getProductByName(name);
        if (productDTO == null) {
            throw new ProductNotFoundException("Product with this name is not found!");
        }
        return productDTO;
    }

    @PutMapping("/{name}")
    public void update(@PathVariable(name = "name") String name, @RequestBody ProductDTO productDTO) {
        productService.updateProduct(name, productDTO);
    }

    @DeleteMapping("/{name}")
    public void deleteByName(@PathVariable(name = "name") String name) {
        productService.deleteProductByName(name);
    }
}
