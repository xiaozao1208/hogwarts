package com.appium;

import io.appium.java_client.android.AndroidDriver;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class HelloWorldTest {

        private AndroidDriver driver;

        @BeforeEach
        public void setUp() throws MalformedURLException {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability("platformName", "android");
            desiredCapabilities.setCapability("udid","127.0.0.1:7555");
            desiredCapabilities.setCapability("deviceName", "127.0.0.1:7555  device");
            desiredCapabilities.setCapability("appPackage", "com.xueqiu.android");
            desiredCapabilities.setCapability("appActivity", "com.xueqiu.android.common.MainActivity");
            desiredCapabilities.setCapability("noReset","true");

            URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

            driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        }

        @Test
        public void xueQiuSearch() {

            //定位首页搜索框
            driver.findElementById("com.xueqiu.android:id/home_search").click();

            //输入搜索内容，
            driver.findElementById("com.xueqiu.android:id/search_input_text").sendKeys("阿里巴巴");
        }



        @AfterEach
        public void tearDown() {
            driver.quit();
        }
}


