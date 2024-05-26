package com.shop.PetProject.exchange;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

@Component
public class ExchangeRateProvider {

    private static final BigDecimal DEFAULT_RATE = BigDecimal.valueOf(1.00);
    private static final String jsonCurrenciesFileName = "src/main/resources/exchange-rate.json";

    public BigDecimal getExchangeRate(Currency currency) {
        return Optional.ofNullable(getExchangeRateFromFile(currency))
                .orElse(DEFAULT_RATE);
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
    private BigDecimal getExchangeRateByCurrency(ExchangeRate exchangeRate, Currency currency) {
        return switch (currency) {
            case USD -> exchangeRate.getUsdExchangeRate();
            case EUR -> exchangeRate.getEurExchangeRate();
            case GBP -> exchangeRate.getGbpExchangeRate();
        };
    }


}
