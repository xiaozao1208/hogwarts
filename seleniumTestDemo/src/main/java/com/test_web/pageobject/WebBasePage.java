package com.test_web.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * 重构问题
 * 1.driver 由静态变为非静态，解决静态时多并发容易出问题的问题 -多线程消耗内存，不安全
 *   driver,wait 父类中初始化
 * 2.创建父类，让其他继承
 */

public class WebBasePage {

    RemoteWebDriver driver;
    WebDriverWait wait;


    //构造器
    public WebBasePage(RemoteWebDriver driver){
        this.driver = driver;
    //   selenium 4.0新版本写法
    //   wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, 10);
    }


    public WebBasePage(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }


    //click方法封装
    public void click(By by){
        wait.until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).click();
    }

    //sendKeys 方法封装
    public void sendKeys(By by,String content){

        driver.findElement(by).sendKeys(content);
    }

    public  void upload(By by, String path){
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        driver.findElement(by).sendKeys(path);
    }


    @Override
    public  void  action(HashMap<String,Object> map){
        super.action(map);
        if(map.get("action").toString().toLowerCase().equals("get")){
            driver.get(map.get("url").toString());
        }
    }

    @Override
    public  void  click(HashMap<String,Object> map){
        super.click();
        String key = (String) map.keySet().toArray()[0];
        String value = (String) map.values().toArray()[0];

        By by = null;
        if(key.toLowerCase() .equals("id")) {
            by = By.id(value);
        }

        if(key.toLowerCase().equals("linkText".toLowerCase())){
            by=By.linkText(value);
        }

        if(key.toLowerCase() .equals("partialLinkText".toLowerCase())){
            by=By.partialLinkText(value);
        }

        click(by);
    }
    public void quit() {
        driver.quit();
    }

}
