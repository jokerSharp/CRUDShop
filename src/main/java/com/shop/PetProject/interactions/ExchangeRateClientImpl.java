package com.shop.PetProject.interactions;

import com.shop.PetProject.exchange.Currency;
import com.shop.PetProject.exchange.CurrencyProvider;
import com.shop.PetProject.exchange.ExchangeRate;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExchangeRateClientImpl implements ExchangeRateClient {
    private final RestTemplate restTemplate;
    private final CurrencyProvider currencyProvider;

    public ExchangeRateClientImpl(RestTemplate restTemplate, CurrencyProvider currencyProvider) {
        this.restTemplate = restTemplate;
        this.currencyProvider = currencyProvider;
    }

    @Override
    @Cacheable(value = "exchangeRate", unless = "#result == null")
    public ExchangeRate getExchangeRate() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("currency", String.valueOf(currencyProvider.getCurrency()));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        try {
            ResponseEntity<ExchangeRate> response = restTemplate.exchange("/api/v1/rate",
                    HttpMethod.GET,
                    entity,
                    ExchangeRate.class);
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }
}
