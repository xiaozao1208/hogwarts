package com.testcaseinfo.testcase;

import org.junit.jupiter.api.*;

//@DisplayName("Junit5测试组")
public class Junit5Demo1Test {

    @BeforeAll
    static void beforeAll(){
        System.out.println("before all");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("after all");
    }


    @BeforeEach
    void  beforeEach(){
        System.out.println("before each ");
    }

    @AfterEach
    void afterEach(){
        System.out.println("after each ");
    }

    @DisplayName("test1测试方法")
    @Test
    void test1(){
        System.out.println("tes1t 执行");
    }

    @Disabled
    @Test
    void test2(){
        System.out.println("test2 执行");
    }

    @Test
    @RepeatedTest(5)
    void test3(){
        System.out.println("test3 执行");
    }


}
