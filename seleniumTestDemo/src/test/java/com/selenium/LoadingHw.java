package com.selenium;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoadingHw {
    public static WebDriver driver;

    /*初始化数据 initData*/
    @BeforeAll
    public static void initData(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        /* 此处建议使用隐式等待，不要使用强制等待*/
    }

    @Test
    public  void  loading(){

        driver.get("https://ceshiren.com/");

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        //点击登录，弹出页面
        driver.findElement(By.xpath("//span[contains(text(),'登录')]")).click();

        driver.findElement(By.id("login-account-name")).click();
        driver.findElement(By.id("login-account-name")).sendKeys("lijuanshy@163.com");

        driver.findElement(By.id("login-account-password")).clear();
        driver.findElement(By.id("login-account-password")).sendKeys("testdaniu147");

        driver.findElement(By.id("login-button")).click();

        /**
         *Thread.sleep(4000); 不建议使用，建议使用隐式等待、
         *
         */
    }

    @AfterAll
    public static  void  near(){
        driver.quit();
    }
}
