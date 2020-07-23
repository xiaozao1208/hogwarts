package com.test_web.selenium;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * 本次重点还是在元素的定位，页面的切换；
 * 获取当前页面的句柄，获取所有页面的句柄，通过切换句柄切换操作；
 */

public class WindowsTest {

    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeAll
    public static void initData(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterAll
    public static  void tearDown(){ driver.quit(); }


    @Test
    public  void  switchWindows(){

        //进入百度首页并最大化
        driver.get("https://www.baidu.com");
        driver.manage().window().maximize();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //定位到右侧登录按钮  这个地方定位错误，注意定位问题,id处少写了 @
//        driver.findElement(By.xpath("//*[@id='u1']/a[last()]")).click();
        driver.findElement(By.xpath("//*[@id='u1']/a[2]")).click();


        //点击注册
        driver.findElement(By.xpath("//*[@id='passport-login-pop-dialog']/div/div/div/div[3]/a")).click();

        //*[@id="u"]/a[3]
        //获取百度首页当前的句柄
        String baiduWin = driver.getWindowHandle();

        //获取全部的句柄--多个
        for(String win:driver.getWindowHandles() ){
                if( !win.equals(baiduWin)){
                    driver.switchTo().window(win);
                    driver.findElement(By.id("TANGRAM__PSP_4__userName")).sendKeys("小早");
                    driver.findElement(By.id("TANGRAM__PSP_4__phone")).sendKeys("133333333");

                    driver.switchTo().window(baiduWin);

                    //*[@id="TANGRAM__PSP_11__footerULoginBtn"]
                    //*[@id="u1"]/a[2]
                    // 切回百度页面后---点击使用【用户名登录】
                    driver.findElement(By.id("TANGRAM__PSP_11__footerULoginBtn")).click();
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

    }

}
