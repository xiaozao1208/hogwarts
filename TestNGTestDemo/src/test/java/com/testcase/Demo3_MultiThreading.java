package com.testcase;

/**
 * 解决性能瓶颈的问题——多线程设置
 */

import org.testng.annotations.Test;

public class Demo3_MultiThreading {

    @Test()
    public void putIntA() throws Exception{
        Thread.sleep(1000);
        System.out.println("装入A ");
    }

    @Test
    public void putIntB() throws Exception{
        Thread.sleep(1000);
        System.out.println("装入B ");
    }


    @Test(threadPoolSize=5,invocationCount = 5,timeOut = 6000)
    public void putIntC() throws Exception{
        Thread.sleep(1000);
        long id = Thread.currentThread().getId();
        System.out.println("线程号"+id+"装入C ");
    }

    @Test
    public void putIntD() throws Exception{
        Thread.sleep(1000);
        System.out.println("装入D ");
    }

    @Test
    public void putIntE() throws Exception{
        Thread.sleep(1000);
        System.out.println("装入E ");
    }

}
