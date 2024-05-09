package com.shop.PetProject.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductErrorResponse {
    private String message;
    private long timestamp;
}
