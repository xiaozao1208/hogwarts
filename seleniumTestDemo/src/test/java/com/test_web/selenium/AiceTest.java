package com.test_web.selenium;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AiceTest {
    public static WebDriver driver;
    public  static WebDriverWait wait;

    /*初始化数据 initData*/
    @BeforeAll
    public static void initData(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        /* 此处建议使用隐式等待，不要使用强制等待*/

        //显式等待的写法----单位可以调整，不一定是秒
        wait = new WebDriverWait(driver,5);
    }

    @AfterAll
    public static  void  near(){
        driver.quit();
    }


    @Test
    public  void  loading(){

        //填写url
        driver.get("https://ceshiren.com/");


        //点击登录，弹出页面
        driver.findElement(By.xpath("//span[contains(text(),'登录')]")).click();

        //清空输入框的内容，然后输入用户名；
        driver.findElement(By.id("login-account-name")).click();
        driver.findElement(By.id("login-account-name")).sendKeys("lijuanshy@163.com");

        //清空输入框的内容，然后输入密码；
        driver.findElement(By.id("login-account-password")).clear();
        driver.findElement(By.id("login-account-password")).sendKeys("testdaniu147");

        //点击登录按钮
        driver.findElement(By.id("login-button")).click();

        /**
         *Thread.sleep(4000); 不建议使用，建议使用隐式等待、
         *
         */

        //todo:assert 如果获取的用户名是Laura_007说明登录成
        WebElement i = driver.findElement(By.xpath("//*[@id='current-user']/a/div/img"));
        assertEquals("Laura_007",i.getAttribute("title"));

    }

    @Test
    public void  timeSleepTest(){
        driver.get("https://ceshiren.com/");

        try {
            /*//会强制等待5秒钟  不推荐使用*/
            Thread.sleep(5000);

            driver.findElement(By.xpath("//span[contains(text(),'登录')]")).click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Test
    public  void  waitTest(){

        driver.get("https://ceshiren.com/");
//        driver.findElement(By.xpath("//span[contains(text(),'登录')]")).click();

       // 现实等待的第二种写法：
//        WebElement loginEle = wait.until(new ExpectedCondition<WebElement>(){
//            public WebElement apply(WebDriver driver){
//                return driver.findElement(By.xpath("//span[contains(text(),'登录')]"));
//            }
//        });
//
//        loginEle.click();

        /*
        现实等待的第一种写法：
        定义变量 wait以及 new WebDriverWait() + 下面内容才可以一起使用
        显示等待：ExpectedConditions 此处需要加s
        ExpectedConditions.presenceOfElementLocated()  等待元素定位成功并出现
        */
        WebElement element  = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'登录')]")));
        element.click();
    }


}
