package com.shop.PetProject.services;

import com.shop.PetProject.dtos.ProductDTO;
import com.shop.PetProject.models.ProductEntity;
import com.shop.PetProject.repositories.ProductRepository;
import com.shop.PetProject.utils.ProductNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;

import static com.shop.PetProject.testUtils.builders.ProductBuilder.getProductDTO;
import static com.shop.PetProject.testUtils.builders.ProductBuilder.getProductEntity;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ConversionService conversionService;

    @Test
    void saveProduct_validProduct_saveProduct() {
        ProductEntity productEntity = getProductEntity();
        ProductDTO productDTO = getProductDTO();
        productService.saveProduct(productDTO);
        Mockito.verify(productRepository, Mockito.times(1)).save(conversionService.convert(productDTO, ProductEntity.class));
    }

    @Test
    void getProductByName_noProduct_throwsException() {
        Assertions.assertThrows(ProductNotFoundException.class, () -> productService.getProductByName("Pineapple"));
    }
}
