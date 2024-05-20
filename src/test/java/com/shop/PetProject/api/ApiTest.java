package com.shop.PetProject.api;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.BooleanSupplier;

import static com.shop.PetProject.api.ApiSettings.createProductDTO;
import static com.shop.PetProject.testUtils.builders.ProductBuilder.getRandomProductDTO;

public class ApiTest {

    private static String createProduct = "/product";


    @Test
    public void createProduct() {
        ValidatableResponse response = ApiDefaultReq.createRequest(createProduct, createProductDTO(getRandomProductDTO()));
        response.assertThat().statusCode(200);
    }
}
