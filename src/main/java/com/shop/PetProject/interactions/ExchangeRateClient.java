package com.shop.PetProject.interactions;

import com.shop.PetProject.exchange.ExchangeRate;
import org.springframework.stereotype.Component;

@Component
public interface ExchangeRateClient {
    ExchangeRate getExchangeRate();
}
