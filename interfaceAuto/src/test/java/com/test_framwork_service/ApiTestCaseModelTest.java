package com.test_framwork_service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ApiTestCaseModelTest {

    private static BaseApi baseApi;
    private static ApiTestCaseModel apiTestcase;

    @BeforeAll
    static void beforeAll() throws IOException {
         baseApi = new BaseApi();
         baseApi.load("D:\\ideaproject\\hogwarts\\interfaceAuto\\src\\main\\resources\\test_framwork_service\\api");
        apiTestcase = ApiTestCaseModel.load("D:\\ideaproject\\hogwarts\\interfaceAuto\\src\\main\\resources\\test_framwork_service\\testcase\\test_add.yaml");
    }


    @Test
    void load() {
        assertThat(apiTestcase.name,equals("add"));
    }


    @Test
    void  run(){
        apiTestcase.run(baseApi);
    }
}