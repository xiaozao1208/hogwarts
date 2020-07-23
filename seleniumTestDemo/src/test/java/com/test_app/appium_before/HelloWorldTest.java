package com.test_app.appium_before;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class HelloWorldTest {

//        private AndroidDriver driver;

        private static AppiumDriver driver;
        private  WebDriverWait wait ;

        @BeforeAll
        public static  void setUp() throws MalformedURLException {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability("platformName", "android");
            desiredCapabilities.setCapability("udid","127.0.0.1:7555");
            desiredCapabilities.setCapability("deviceName", "127.0.0.1:7555  device");
            desiredCapabilities.setCapability("appPackage", "com.xueqiu.android");
            desiredCapabilities.setCapability("appActivity", ".view.WelcomeActivityAlias");
            desiredCapabilities.setCapability("noReset","true");

            URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");



            //todo:make sure
            driver = new AndroidDriver<MobileElement>(remoteUrl, desiredCapabilities);

            //添加隐式等待
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        }


        @Test
        public  void  fun(){

            wait = new WebDriverWait(driver,10,1);
            wait.until(ExpectedConditions.
                    visibilityOfElementLocated(MobileBy.id("com.android.settings:id/title")));
        }


        @Test
        public void xueQiuSearch() {

            //定位首页搜索框
            driver.findElement(By.id("com.xueqiu.android:id/home_search")).click();

            //输入搜索内容，
            driver.findElementById("com.xueqiu.android:id/search_input_text").sendKeys("阿里巴巴");
        }



        @AfterAll
        public static  void tearDown() {
            driver.quit();
        }
}


