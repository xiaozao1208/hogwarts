package com.test_app.snowball.page;


import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MainPage {

    private AndroidDriver driver;

    /**
     * 主页面 设计登录
     */
    public MainPage(){

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "android");
        caps.setCapability("deviceName", "127.0.0.1:7555  device");
        caps.setCapability("appPackage", "com.xueqiu.android");
        caps.setCapability("appActivity", ".view.WelcomeActivityAlias");
        caps.setCapability("noReset","true");

        URL remoteUrl = null;

        try {
            remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //todo:make sure
        driver = new AndroidDriver(remoteUrl, caps);

        //需要隐式等待
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //todo:等待优化
    }



    //进入搜索
    public SearchPage toSearch(){

        MobileElement el1 = (MobileElement) driver.findElementById("com.xueqiu.android:id/home_search");
        el1.click();

        return new SearchPage(driver);
    }


    public  void  toStock(){

    }

    public  void  quit(){
        driver.quit();
    }

}
