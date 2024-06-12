package com.shop.PetProject.controllers.requests;

import com.shop.PetProject.dtos.product.ProductDTO;
import com.shop.PetProject.models.OrderTotal;
import lombok.Builder;

import java.util.Set;

@Builder
public record OrderRequest(Set<OrderedProductInfo> orderedProductInfo,
                           Long customerId) {
}
