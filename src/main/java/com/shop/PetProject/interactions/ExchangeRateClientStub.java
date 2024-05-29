package com.shop.PetProject.interactions;

import com.shop.PetProject.exchange.ExchangeRate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@Primary
@ConditionalOnProperty(value = "interaction.rate.stub", havingValue = "true", matchIfMissing = false)
public class ExchangeRateClientStub implements ExchangeRateClient{

    @Override
    public ExchangeRate getExchangeRate() {
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setUsdExchangeRate(BigDecimal.valueOf(2));
        exchangeRate.setEurExchangeRate(BigDecimal.valueOf(3));
        exchangeRate.setGbpExchangeRate(BigDecimal.valueOf(4));
        exchangeRate.setCreationTime(LocalDateTime.now());

        return exchangeRate;
    }
}
