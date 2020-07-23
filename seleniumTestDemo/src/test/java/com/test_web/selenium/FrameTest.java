package com.test_web.selenium;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class FrameTest {

    public static WebDriver driver;

    @BeforeAll
    public static void initData() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    /**
     *
     */
    @Test
    public void frameTest() {

        //进入url
        driver.get("https://www.runoob.com/try/try.php?filename=jqueryui-api-droppable");

        //切换到右边的iframe
        driver.switchTo().frame("iframeResult");
        System.out.println(driver.findElement(By.id("draggable")).getText());

        //切换到另一个页面
        driver.switchTo().parentFrame();

        //点击运行
        System.out.println(driver.findElement(By.id("submitBTN")).getText());


    }
}