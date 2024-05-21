package com.shop.PetProject.api;

import com.shop.PetProject.dtos.ProductDTO;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.BooleanSupplier;

import static com.shop.PetProject.api.ApiSettings.createProductDTO;
import static com.shop.PetProject.testUtils.builders.ProductBuilder.getEmptyProductDTO;
import static com.shop.PetProject.testUtils.builders.ProductBuilder.getRandomProductDTO;

public class ApiTest {

    private static String createProduct = "/product";


    @Test
    public void createProduct() {
        ValidatableResponse response = ApiDefaultReq.createRequest(createProduct, createProductDTO(getRandomProductDTO()));
        response.assertThat().statusCode(200);
    }

    @Test
    public void createEmptyProductAttempt() {
        ValidatableResponse response = ApiDefaultReq.createRequest(createProduct, createProductDTO(getEmptyProductDTO()));
        response.assertThat().statusCode(400);
    }
}
