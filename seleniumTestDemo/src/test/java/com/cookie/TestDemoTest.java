package com.cookie;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * 浏览器的复用----重点
 */
public class TestDemoTest {

    WebDriver driver;

    @BeforeEach
    public  void setUp() {

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        driver = new ChromeDriver(options);
    }


//    @Test
//    public void  testBaidu(){
//
//        try {
//            driver.get("https://www.baidu.com");
//            driver.findElement(By.id("kw")).sendKeys("张三丰");
//            driver.findElement(By.id("su")).click();
//            driver.findElement(By.xpath("//*[@id='1']/h3/a")).click();
//
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }


    //todo： 复用失败，原因是什么？
    //todo: @BeforeEach错用成@Before，另一个是元素定位不准确。
    @Test
    public void  testDemo(){
        driver.findElement(By.xpath("//*[@id='menu_apps']/span")).click();
    }


    @AfterEach
    public void tearDown(){
        driver.quit();
    }

}


