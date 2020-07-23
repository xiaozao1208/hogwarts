package com.test_web.pageobject;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestAddMember {

    @Test
    public  void  testAdd(){

        WebDriver driver;
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        driver = new ChromeDriver(options);

        //点击添加成员
        driver.findElement(By.linkText("添加成员")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath("//*[@id='username']")).sendKeys("4");
        driver.findElement(By.name("acctid")).sendKeys("4");
        driver.findElement(By.name("mobile")).sendKeys("17301670004");

        //点击保存
        driver.findElement(By.cssSelector(".js_btn_save")).click();


    }
}
