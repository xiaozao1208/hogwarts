package com.temp;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestDemo {

    private AndroidDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "android");
//        caps.setCapability("udid","127.0.0.1:7555");
        caps.setCapability("deviceName", "127.0.0.1:7555  device");
        caps.setCapability("appPackage", "com.xueqiu.android");

        //com.xueqiu.android.common.MainActivity 跟页面上查到的不一致？？
        caps.setCapability("appActivity", ".view.WelcomeActivityAlias");
        caps.setCapability("noReset","true");

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        //todo:make sure
        driver = new AndroidDriver(remoteUrl, caps);

        //需要隐式等待
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //todo:等待优化
    }

    @Test
    public void snowBallSearch() {

        MobileElement el1 = (MobileElement) driver.findElementById("com.xueqiu.android:id/home_search");
        el1.click();
        MobileElement el2 = (MobileElement) driver.findElementById("com.xueqiu.android:id/search_input_text");
        el2.click();
        el2.sendKeys("阿里巴巴");
        MobileElement el3 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]");
        el3.click();
        MobileElement el4 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.LinearLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.RelativeLayout/android.widget.LinearLayout[1]");
        el4.click();


    }



    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
