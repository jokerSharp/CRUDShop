package com.shop.PetProject.controllers.requests;

import lombok.Builder;

import java.util.Set;

@Builder
public record OrderRequest(Set<OrderedProductInfo> orderedProductInfo,
                           Long customerId) {
}
