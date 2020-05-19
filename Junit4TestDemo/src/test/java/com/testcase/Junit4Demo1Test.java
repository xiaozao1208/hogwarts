package com.testcase;

import org.junit.*;

public class Junit4Demo1Test {

    @BeforeClass
    public  static void  beforeClass(){
        System.out.println("before class");
    }

    @AfterClass
    public static void  afterClass(){
        System.out.println("after class");
    }

    @Before
    public void  before(){
        System.out.println("before");
    }

    @Test
    public  void fun1(){
        System.out.println("test1 ");
    }

    @Ignore
    @Test
    public void  fun2(){
        System.out.println("test2");
    }

    @After
    public  void  after(){
        System.out.println("after");
    }


}
