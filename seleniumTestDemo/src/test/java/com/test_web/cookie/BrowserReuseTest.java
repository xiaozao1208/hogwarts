package com.test_web.cookie;

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
public class BrowserReuseTest {

    WebDriver driver;

    @BeforeEach
    public  void setUp() {

        /**
         * 下面三行代码是复用浏览器的代码，还需要两个前提条件：
         * 1.其他浏览器都关闭；2.这个浏览器一直开着，不要关；
         */
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        driver = new ChromeDriver(options);
        //driver = new ChromeDriver(); 如果不想复用浏览器，直接去掉options即可；
    }


    @Test
    public void  testBaidu(){

        try {
            driver.get("https://www.baidu.com");
            driver.findElement(By.id("kw")).sendKeys("张三丰");
            driver.findElement(By.id("su")).click();

            Thread.sleep(2000);
            driver.findElement(By.xpath("//div[@id='1']/h3/a")).click();

            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


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


