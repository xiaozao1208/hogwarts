package com.selenium;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WindowsTest {

    public static WebDriver driver;

    @BeforeAll
    public static void initData(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @AfterAll
    public static  void tearDown(){
        driver.quit();
    }


    @Test
    public  void  switchWindows(){
        driver.get("https://www.baidu.com");
        driver.manage().window().maximize();

        //这个地方定位错误，注意定位问题
        driver.findElement(By.xpath("//*[id='u1']/a[last()]")).click();

        //点击注册
        driver.findElement(By.xpath("//*[@id='passport-login-pop-dialog']/div/div/div/div[4]/a")).click();

        //*[@id="u"]/a[3]
        //获取百度首页的句柄
        String baiduWin = driver.getWindowHandle();
        for(String win:driver.getWindowHandles() ){
                if( !win.equals(baiduWin)){
                    driver.switchTo().window(win);
                    driver.findElement(By.id("TANGRAM__PSP_4__userName")).sendKeys("小早");
                    driver.findElement(By.id("TANGRAM__PSP_4__phone")).sendKeys("133333333");

                    driver.switchTo().window(baiduWin);

                    driver.findElement(By.xpath("//*[id='u']/a[3]")).click();
                    driver.findElement(By.id("TANGRAM__PSP_11__userName")).sendKeys("17301673373");
                    driver.findElement(By.id("TANGRAM__PSP_11__password")).sendKeys("testdaniu147");
                    driver.findElement(By.id("TANGRAM__PSP_11__submit")).click();


                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }


        //*[@id="passport-login-pop-dialog"]/div/div/div/div[3]/a


    }

}
