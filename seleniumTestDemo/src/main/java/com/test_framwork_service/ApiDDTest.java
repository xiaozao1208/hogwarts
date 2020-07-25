package com.test_framwork_service;

import io.restassured.specification.Argument;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApiDDTest {


    private static BaseApi baseApi;

    @BeforeAll
    static void  beforeAll(){

    }

    @BeforeEach
    void  ww(){

    }


    @ParameterizedTest(name="{index}{1}")
    @MethodSource
    void apiTest(ApiTestCaseModel apiTestCaseModel,String name){
        apiTestCaseModel.run(baseApi);
    }

    static List<Argument> apiTest(){

        baseApi = new BaseApi();
        baseApi.load("");

        List<Argument> testcases = new ArrayList<>();
        Arrays.asList("")
                .stream().forEach(pathTestCase->{
            ApiTestCaseModel   apiTestCase =  ApiTestCaseModel.load(pathTestCase);
            testcases.add(arguments(apiTestCase));
        });
    }
}
