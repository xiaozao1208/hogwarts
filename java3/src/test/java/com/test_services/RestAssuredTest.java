package com.test_services;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredTest {

    @Test
    void fun(){
        given()
                .get("https://www.baidu.com")
                .then()
                .statusCode(200)
                .log().all();

    }

}
