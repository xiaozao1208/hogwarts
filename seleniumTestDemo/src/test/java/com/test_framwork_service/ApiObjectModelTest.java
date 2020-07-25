package com.test_framwork_service;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ApiObjectModelTest {

    private static ApiObjectModel api;


    @BeforeAll
    static void beforeAll() {
        api = ApiObjectModel.load("");
    }

    @Test
    void load() {
        assertThat(api.name,equals("wework"));
    }

    @Test
    void run() {
        Response response = api.methods.get().run();
        response.then().statusCode(200);
    }
}