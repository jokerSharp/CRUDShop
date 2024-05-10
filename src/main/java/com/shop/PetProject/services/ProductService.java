package com.shop.PetProject.services;

import com.shop.PetProject.dtos.ProductDTO;
import com.shop.PetProject.models.ProductEntity;
import com.shop.PetProject.repositories.ProductRepository;
import com.shop.PetProject.utils.ProductNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ConversionService conversionService;


    public Page<ProductDTO> getProductsPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable).map(product -> conversionService.convert(product, ProductDTO.class));
    }

    public List<ProductEntity> getProduct() {
        return productRepository.findAll();
    }

    public ProductEntity getProductByName(String name) {
        return productRepository.findByName(name).stream()
                .findFirst()
                .orElse(null);
    }

    @Transactional
    public void saveProduct(ProductEntity productEntity) {
        productEntity.setCreationDate(LocalDateTime.now());
        productEntity.setQuantityChange(LocalDateTime.now());
        productRepository.save(productEntity);
    }

    @Transactional
    public void updateProduct(String name, ProductEntity updatedProductEntity) {
        ProductEntity productEntity = productRepository.findByName(name).stream()
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product with this name is not found!"));
        updatedProductEntity.setId(productEntity.getId());
        updatedProductEntity.setCreationDate(productEntity.getCreationDate());
        if (productEntity.getQuantity() == updatedProductEntity.getQuantity()) {
            updatedProductEntity.setQuantityChange(LocalDateTime.now());
        }
        productRepository.save(updatedProductEntity);
    }

    @Transactional
    public void deleteProductByName(String name) {
        productRepository.findByName(name).stream()
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product with this name is not found!"));
        productRepository.deleteByName(name);
    }
}
