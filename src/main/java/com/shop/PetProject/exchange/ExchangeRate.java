package com.shop.PetProject.exchange;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExchangeRate {
    private BigDecimal usdExchangeRate;
    private BigDecimal eurExchangeRate;
    private BigDecimal gbpExchangeRate;
}
