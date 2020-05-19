package com.testcase;

import org.testng.annotations.*;

public class Demo_Flow {

    @BeforeSuite
    public  void  beforeSuite(){
        System.out.println("BeforeSuite");
    }

    @AfterSuite
    public void  afterSuite(){
        System.out.println("afterSuite");
    }

    @BeforeTest
    public void  beforeTest(){
        System.out.println("beforeTest");
    }


    @AfterTest
    public void  afterTest(){
        System.out.println("afterTest");
    }


    @BeforeClass
    public void  eforeClass(){
        System.out.println("beforeClass");
    }


    @AfterClass
    public void  afterClass(){
        System.out.println("afterClass");
    }

    @BeforeMethod
    public void  beforeMethod(){
        System.out.println("beforeMethod");
    }

    @AfterMethod
    public void  afterMethod(){
        System.out.println("afterMethod");
    }


    @Test
    public void  test1(){
        System.out.println("test1 ");
    }

    @Test(enabled=false)
    public void  test2(){
        System.out.println("test2 ");
    }
}
