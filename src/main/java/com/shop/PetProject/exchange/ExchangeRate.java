package com.shop.PetProject.exchange;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ExchangeRate {
    private BigDecimal usdExchangeRate;
    private BigDecimal eurExchangeRate;
    private BigDecimal gbpExchangeRate;
    private LocalDateTime creationTime;
}
