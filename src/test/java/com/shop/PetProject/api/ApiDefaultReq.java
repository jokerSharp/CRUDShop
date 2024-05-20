package com.shop.PetProject.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class ApiDefaultReq {

    public static ValidatableResponse createRequest(String endpoint, RequestSpecification specification) {
        ValidatableResponse response = RestAssured
                .given()
                .when()
                    .spec(specification)
                    .post(endpoint)
                .then()
                    .log().all();
        return response;
    }
}
