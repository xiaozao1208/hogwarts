package com.testcase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class Demo_RecordAfterPutin2 {


    @Test
    public void putIntA() throws Exception{
        System.out.println("装入A ");
    }

    @Test
    public void putIntB() throws Exception{
        System.out.println("装入B ");
    }


    @Test
    public void putIntC() throws Exception{
        System.out.println("装入C ");
    }

    @Test
    public void putIntD() throws Exception{
        System.out.println("装入D ");
    }

    @Test
    public void putIntE() throws Exception{
        System.out.println("装入E ");
    }

    @AfterMethod
    public  void  record(){
        System.out.println("小哥记录下");
    }

}
