package com.shop.PetProject.services;

import com.shop.PetProject.dtos.ProductDTO;
import com.shop.PetProject.models.ProductEntity;
import com.shop.PetProject.repositories.ProductRepository;
import com.shop.PetProject.testUtils.builders.converters.ManualConverter;
import com.shop.PetProject.utils.ProductNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import java.util.List;

import static com.shop.PetProject.testUtils.builders.ProductBuilder.getProductDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        ProductDTO productDTO = getProductDTO();
        ProductEntity productEntity = ManualConverter.convert(productDTO);
        when(conversionService.convert(productDTO, ProductEntity.class))
                .thenReturn(productEntity);
        productService.saveProduct(productDTO);
        verify(productRepository, times(1))
                .save(any(ProductEntity.class));
    }

    @Test
    void getProductByName_productExists_getProduct() {
        ProductDTO productDTO = getProductDTO();
        ProductEntity productEntity = ManualConverter.convert(productDTO);
        Mockito.when(productRepository.findByName(productEntity.getName())).thenReturn(List.of(productEntity));
        productService.getProductByName(productEntity.getName());
        verify(productRepository, times(1)).findByName(anyString());
    }

    @Test
    void getProductByName_noProduct_throwsException() {
        Assertions.assertThrows(ProductNotFoundException.class, () -> productService.getProductByName("Pineapple"));
    }
}
