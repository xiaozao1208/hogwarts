package com.test_framwork_service;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ApiObjectModelTest {

    private static ApiObjectModel api;


    @BeforeAll
    static void beforeAll() throws IOException {
        api = ApiObjectModel.load("D:\\ideaproject\\hogwarts\\interfaceAuto\\src\\main\\resources\\test_framwork_service\\api\\wework_api.yaml");
    }

    @Test
    void load() {
        assertThat(api.name,equals("wework"));
    }

    @Test
    void run() {
        Response response = api.methods.get("get_token").run();
        response.then().statusCode(200);
    }
}