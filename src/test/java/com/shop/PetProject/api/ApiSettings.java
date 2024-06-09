package com.shop.PetProject.api;

import com.shop.PetProject.dtos.product.ProductDTO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ApiSettings {
    private static String baseUrl = "http://localhost:8080/api/v1";

    public static RequestSpecification createProductDTO(ProductDTO productDTO) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .setBody(productDTO)
                .build();
    }
}
