package com.shop.PetProject.dtos.order;

import com.shop.PetProject.dtos.product.ProductDTO;
import com.shop.PetProject.models.OrderStatuses;
import lombok.Builder;

import java.util.Set;

@Builder
public record OrderDTO(Long id,
                       Set<ProductDTO> products,
                       Long customerId,
                       OrderStatuses status,
                       Integer totalPrice) {
}
