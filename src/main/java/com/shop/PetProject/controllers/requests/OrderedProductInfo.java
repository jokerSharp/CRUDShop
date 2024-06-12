package com.shop.PetProject.controllers.requests;

import lombok.Builder;

@Builder
public record OrderedProductInfo(String productName,
                                 Integer quantity) {
}
