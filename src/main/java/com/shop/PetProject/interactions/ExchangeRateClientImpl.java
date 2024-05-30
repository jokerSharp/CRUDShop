package com.shop.PetProject.interactions;

import com.shop.PetProject.exchange.ExchangeRate;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExchangeRateClientImpl implements ExchangeRateClient {
    private final RestTemplate restTemplate;

    public ExchangeRateClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @Cacheable(value = "exchangeRate", unless = "#result == null")
    public ExchangeRate getExchangeRate() {
        try {
            return restTemplate.getForObject("/api/v1/rate", ExchangeRate.class);
        } catch (Exception e) {
            return null;
        }
    }
}
