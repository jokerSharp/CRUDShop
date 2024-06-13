package com.shop.PetProject.controllers.responses;

import com.shop.PetProject.models.OrderStatuses;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class GetOrderResponse {

    Long orderId;

    String customerName;

    BigDecimal totalPrice;

    OrderStatuses status;
}
