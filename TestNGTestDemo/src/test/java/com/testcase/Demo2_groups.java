package com.testcase;

import org.testng.annotations.Test;

public class Demo2_groups {

    @Test(groups = {"group01"})
    public void putIntA() throws Exception{
        System.out.println("装入A ");
    }

    @Test(groups = {"group01"})
    public void putIntB() throws Exception{
        System.out.println("装入B ");
    }


    @Test(groups = {"group01","group02"})
    public void putIntC() throws Exception{
        System.out.println("装入C ");
    }

    @Test(groups = {"group02"})
    public void putIntD() throws Exception{
        System.out.println("装入D ");
    }

    @Test(groups = {"group02"})
    public void putIntE() throws Exception{
        System.out.println("装入E ");
    }

}
