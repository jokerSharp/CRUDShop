package com.shop.PetProject.configs;

import com.shop.PetProject.utils.converters.customers.CustomerDTOToEntityConverter;
import com.shop.PetProject.utils.converters.customers.CustomerEntityToDTOConverter;
import com.shop.PetProject.utils.converters.orders.OrderDTOToEntityConverter;
import com.shop.PetProject.utils.converters.orders.OrderEntityToDTOConverter;
import com.shop.PetProject.utils.converters.products.ProductDTOToEntityConverter;
import com.shop.PetProject.utils.converters.products.ProductDTOToGetResponseConverter;
import com.shop.PetProject.utils.converters.products.ProductEntityToDTOConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

@Configuration
public class ConversionConfig {

    @Bean
    public ConversionService conversionService() {
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new ProductDTOToEntityConverter());
        conversionService.addConverter(new ProductEntityToDTOConverter());
        conversionService.addConverter(new ProductDTOToGetResponseConverter());
        conversionService.addConverter(new CustomerDTOToEntityConverter());
        conversionService.addConverter(new CustomerEntityToDTOConverter());
        conversionService.addConverter(new OrderDTOToEntityConverter());
        conversionService.addConverter(new OrderEntityToDTOConverter());
        return conversionService;
    }
}
