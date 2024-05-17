package com.shop.PetProject.services;

import com.shop.PetProject.dtos.ProductDTO;
import com.shop.PetProject.models.ProductEntity;
import com.shop.PetProject.repositories.ProductRepository;
import com.shop.PetProject.utils.ProductAlreadyExistsException;
import com.shop.PetProject.utils.ProductIntegrityViolationException;
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
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ConversionService conversionService;


    public Page<ProductDTO> getProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable).map(product -> conversionService.convert(product, ProductDTO.class));
    }

    public List<ProductEntity> getProducts() {
        return productRepository.findAll();
    }

    public ProductDTO getProductByName(String name) {
        return conversionService.convert(productRepository.findByName(name).stream()
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product with this name is not found!")), ProductDTO.class);
    }

    @Transactional
    public void saveProduct(ProductDTO productDTO) {
        Optional<ProductEntity> product = productRepository.findByName(productDTO.name()).stream()
                .findFirst();
        if (product.isPresent()) {
            throw new ProductAlreadyExistsException("Product with this name already exists");
        } else {
            ProductEntity productEntity = conversionService.convert(productDTO, ProductEntity.class);
            productEntity.setCreationDate(LocalDateTime.now());
            productEntity.setQuantityChange(LocalDateTime.now());
            productRepository.save(productEntity);
        }
    }

    @Transactional
    public void updateProduct(String name, ProductDTO productDTO) {
        ProductEntity updatedProductEntity = productRepository.findByName(name).stream()
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product with this name is not found!"));
        if (productDTO.quantity() != updatedProductEntity.getQuantity()) {
            updatedProductEntity.setQuantityChange(LocalDateTime.now());
        }
        if (!productDTO.name().equals(updatedProductEntity.getName())
        || !productDTO.article().equals(updatedProductEntity.getArticle())) {
            throw new ProductIntegrityViolationException("Product article or product name should not be changed");
        }
        updatedProductEntity.setPrice(productDTO.price());
        updatedProductEntity.setDescription(productDTO.description());
        updatedProductEntity.setCategory(productDTO.category());
        updatedProductEntity.setQuantity(productDTO.quantity());

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
