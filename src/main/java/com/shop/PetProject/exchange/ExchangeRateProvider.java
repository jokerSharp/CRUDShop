package com.shop.PetProject.exchange;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.PetProject.interactions.ExchangeRateClient;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

@Component
public class ExchangeRateProvider {

    private static final String jsonCurrenciesFileName = "src/main/resources/exchange-rate.json";
    private final ExchangeRateClient exchangeRateClient;

    public ExchangeRateProvider(ExchangeRateClient exchangeRateClient) {
        this.exchangeRateClient = exchangeRateClient;
    }

    public BigDecimal getExchangeRate(Currency currency) {
        return Optional.ofNullable(getExchangeRateFromService(currency))
                .orElseGet(() -> getExchangeRateFromFile(currency));
    }

    private BigDecimal getExchangeRateFromFile(Currency currency) {
        try {
            ObjectMapper om = new ObjectMapper();
            File file = new File(jsonCurrenciesFileName);
            ExchangeRate exchangeRate = om.readValue(file, ExchangeRate.class);
            return getExchangeRateByCurrency(exchangeRate, currency);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private BigDecimal getExchangeRateFromService(Currency currency) {
        return Optional.ofNullable(exchangeRateClient.getExchangeRate())
                .map(rate -> getExchangeRateByCurrency(rate, currency))
                .orElse(null);
    }

    private BigDecimal getExchangeRateByCurrency(ExchangeRate exchangeRate, Currency currency) {
        return switch (currency) {
            case USD -> exchangeRate.getUsdExchangeRate();
            case EUR -> exchangeRate.getEurExchangeRate();
            case GBP -> exchangeRate.getGbpExchangeRate();
        };
    }
}
