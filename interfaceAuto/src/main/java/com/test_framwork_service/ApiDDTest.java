package com.test_framwork_service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ApiDDTest {

    private static BaseApi baseApi;



    @ParameterizedTest(name="{index}{1}")
    @MethodSource
    void apiTest(ApiTestCaseModel apiTestCaseModel,String name){
        apiTestCaseModel.run(baseApi);
    }


    static List<Arguments> apiTest(){

            //加载所有的api object
            baseApi = new BaseApi();
            if (System.getProperty("api") != null) {
                baseApi.load(System.getProperty("api"));
            } else {
//            baseApi.load("src/main/resources/test_framework_service/api");
            }
//      此处的路径是老师的路径，需要更换成自己的，这个地方为什么注释？？


            //用来传递给参数化用例
            //List<Arguments>  此处的Argument是复数，不是单数
            List<Arguments> testcases = new ArrayList<>();

            //读取所有的测试用例
            String testcaseDir = "";
            if (System.getProperty("test") != null) {
                testcaseDir = System.getProperty("test");
            }

            String finalTestcaseDir = testcaseDir;

            Arrays.stream(new File(testcaseDir).list())
                    .forEach(name -> {
                                String path = finalTestcaseDir+"/"+name;
                                ApiTestCaseModel apiTestCase = null;
                        try {
                            apiTestCase = ApiTestCaseModel.load(path);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        testcases.add(arguments(apiTestCase,apiTestCase.name));
                    });

        return testcases;
    }


}
