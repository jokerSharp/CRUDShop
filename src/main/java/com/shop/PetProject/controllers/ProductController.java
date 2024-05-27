package com.shop.PetProject.controllers;

import com.shop.PetProject.controllers.responses.GetProductResponse;
import com.shop.PetProject.dtos.PageResponse;
import com.shop.PetProject.dtos.ProductDTO;
import com.shop.PetProject.dtos.ProductFilter;
import com.shop.PetProject.exchange.ExchangeRate;
import com.shop.PetProject.services.ProductService;
import com.shop.PetProject.utils.ProductAlreadyExistsException;
import com.shop.PetProject.utils.ProductValidator;
import jakarta.validation.Valid;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;
    private final ProductValidator productValidator;
    private final ConversionService conversionService;
    private final RestTemplate restTemplate;


    public ProductController(ProductService productService, ProductValidator productValidator, ConversionService conversionService, RestTemplate restTemplate) {
        this.productService = productService;
        this.productValidator = productValidator;
        this.conversionService = conversionService;
        this.restTemplate = restTemplate;
    }

    @GetMapping()
    public ResponseEntity<List<ProductDTO>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<ProductDTO> productPage = productService.getProducts(page, size);
        List<ProductDTO> products = productPage.getContent();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search")
    public PageResponse<ProductDTO> pageableSearch(ProductFilter filter, Pageable pageable) {
        Page<ProductDTO> productPage = productService.getProducts(filter, pageable);
        PageResponse<ProductDTO> pageResponse = PageResponse.of(productPage);
        return pageResponse;
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
        productService.saveProduct(productDTO);
    }

    @GetMapping("/{name}")
    public ProductDTO details(@PathVariable(name = "name") String name) {
        ProductDTO productDTO = productService.getProductByName(name);
        return productDTO;
    }

    @PatchMapping("/{name}")
    public void update(@PathVariable(name = "name") String name, @RequestBody ProductDTO productDTO) {
        productService.updateProduct(name, productDTO);
    }

    @DeleteMapping("/{name}")
    public void deleteByName(@PathVariable(name = "name") String name) {
        productService.deleteProductByName(name);
    }

    @GetMapping("/exchange/{name}")
    public GetProductResponse getProductByName(@PathVariable(name = "name") String name) {
        ProductDTO productDTO = productService.getProductByName(name);

        return conversionService.convert(productDTO, GetProductResponse.class);
    }

    @GetMapping("/exchange/rate")
    public ExchangeRate getRate() {
        ResponseEntity<ExchangeRate> forEntity = restTemplate.getForEntity("http://localhost:8081/api/v1/rate", ExchangeRate.class);
        return forEntity.getBody();
    }
}
