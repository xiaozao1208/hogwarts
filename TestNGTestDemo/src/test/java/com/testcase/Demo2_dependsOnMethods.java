package com.testcase;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Demo2_dependsOnMethods {

    @Test()
    public void putIntA() throws Exception{
        System.out.println("装入A ");
        Assert.fail("装配发生错误！！");
    }

    @Test(dependsOnMethods = {"putIntA"})
    public void putIntB() throws Exception{
        System.out.println("装入B ");
    }


    @Test(dependsOnMethods = {"putIntB"})
    public void putIntC() throws Exception{
        System.out.println("装入C ");
    }

    @Test(dependsOnMethods = {"putIntC"})
    public void putIntD() throws Exception{
        System.out.println("装入D ");
    }

    @Test(dependsOnMethods = {"putIntD"})
    public void putIntE() throws Exception{
        System.out.println("装入E ");
    }

}
