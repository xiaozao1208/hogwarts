package com.testcaseinfo.testcase;

import org.junit.jupiter.api.*;

//@DisplayName("Junit5测试组")
public class Junit5Demo2Test {

    @BeforeAll
    static void beforeAll(){
        System.out.println("before all");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("after all");
    }



    @Test
    @Tag("uatdemo")
    void test1(){
        System.out.println("tes1t 执行");
    }


    @Test
    @Tag("testdemo")
    void test2(){
        System.out.println("test2 执行");
    }

    @Test
    @Tag("testdemo")
    void test3(){
        System.out.println("test3 执行");
    }

    @Test
    @Tag("uatDemo")
    void test4(){
        System.out.println("test4 执行");
    }
}
