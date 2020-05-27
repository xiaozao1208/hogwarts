package com.selenium;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class InteractiveTest {
    public static WebDriver driver;
//    public  static WebDriverWait wait;

    @BeforeAll
    public static void initData(){
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public  void clickTest(){
        driver.get("https://sahitest.com/demo/clicks.htm");
        Actions action = new Actions(driver);

        action.perform();

    }



    @AfterAll
    public static  void tearDown(){
        driver.quit();
    }
}
