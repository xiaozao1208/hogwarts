package com.test_web.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class UpLoadTest extends BaseTest {

    /**
     * 百度上传本地图片
     */
    @Test
    public  void  upLoad(){

        // loadind www.baidu.com
        driver.get("https://www.baidu.com/");


        // click camera
        driver.findElement(By.xpath("//span[@class='soutu-btn']")).click();

        try {

            Thread.sleep(2000);
                // choose file
            driver.findElement(By.xpath("//input[@class='upload-pic']"))
                    .sendKeys("D:\\ideaproject\\hogwarts\\seleniumTestDemo\\src\\main\\resources\\littledog.jpg");

            // loading time maybe long
            Thread.sleep(20000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
