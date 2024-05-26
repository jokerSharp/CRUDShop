package com.shop.PetProject.exchange;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CurrencyProvider {
    private Currency currency;

    public CurrencyProvider () {
        this.currency = Currency.GBP;
    }
}
