package com.shop.PetProject.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Value("${interaction.rate.baseUrl}")
    private String baseUrl;

    @Bean
    public RestTemplate exchangeRateRestTemplate(RestTemplateBuilder builder) {
        return builder
                .rootUri(baseUrl)
                .build();
    }
}
