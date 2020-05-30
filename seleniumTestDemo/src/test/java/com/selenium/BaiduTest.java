package com.selenium;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

/**
 * 元素定位 xpath
 * Console-clear()--$x('')
 * ('//*[@id="s_tab"]//b')
 * ('//*[@id="s_tab"]//a[last()]')
 *
 */

public class BaiduTest {


    public static WebDriver driver;

    @BeforeAll
    public static void initData() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterAll
    public static  void  near(){
        driver.quit();
    }

    @Test
    public void  elementSearch(){

        driver.get("https://www.baidu.com");

        driver.findElement(By.id("kw")).sendKeys("霍格沃兹测试学院");
        //driver.findElement(By.name("wd")).sendKeys("霍格沃兹测试学院");

        driver.findElement(By.xpath("//*[@id='s_tab']//b")).click();

        driver.findElement(By.xpath("//*[@id='s_tab']//a[last()]"));


    }

}
