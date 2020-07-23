package com.test_web.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;

public class JSTest extends BaseTest{

    /**
     * 打开12306，修改日期
     * js知识可以在 w3c网址了解；
     *
     */

    @Test
    public void jsTest(){

        //进入官网
        driver.get("https://www.12306.cn/index/");

        //定位元素
        JavascriptExecutor  jsDriver = (JavascriptExecutor)driver;

        try {

            Thread.sleep(2000);
            jsDriver.executeScript("document.getElementById('train_date').value='2020-06-18'");

            Thread.sleep(2000);
           Object date= jsDriver.executeScript("return document.getElementById('train_date').value");
            System.out.println(date);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
