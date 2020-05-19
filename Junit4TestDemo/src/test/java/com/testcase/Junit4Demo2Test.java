package com.testcase;

import org.junit.Ignore;
import org.junit.Test;


@Ignore
public class Junit4Demo2Test {


    @Test
    public  void  fun1(){
        System.out.println("fun1");
    }

    @Ignore
    @Test
    public  void  fun2(){
        System.out.println("fun2");
    }

    @Test
    public  void  fun3(){
        System.out.println("fun3");
    }
}
