package com.test_framwork_service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.*;

class BaseApiTest {
    private static BaseApi baseApi;

    @BeforeAll
    static void beforeAll(){
        baseApi = new  BaseApi();
        baseApi.load("D:\\ideaproject\\hogwarts\\seleniumTestDemo\\src\\main\\resources\\test_framwork_service\\api");
    }

    @Test
    void load() {

        assertThat(baseApi.apis.size(),greaterThan(1));
    }


    @Test
    void run() {
        baseApi.run("wework","get_token");
    }
}