package com.test_web.wework.page;

import com.test_framework.BasePage;
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

public class WebBasePage extends BasePage {

    /**
     * 考虑到不同浏览器的兼容性，使用RemoteWebDriver
     */
    RemoteWebDriver driver;
    WebDriverWait wait;

    /**
     * 无参数的构造方法
     */
    public WebBasePage(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }


    //有参数的构造器
    public WebBasePage(RemoteWebDriver driver){
        this.driver = driver;
    //   selenium 4.0新版本写法
    //   wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, 10);
    }

    public void quit() { driver.quit(); }



    //click方法封装
    public void click(By by){
        //todo:异常处理？ why?what?
        // 等待元素可被点击
        wait.until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).click();
    }

    //sendKeys 方法封装
    public void sendKeys(By by,String content){
        //等待定位的元素可见；检查元素是否存在以及元素是否可见
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        driver.findElement(by).sendKeys(content);
    }

    public  void upload(By by, String path){
        //? presenceOfElementLocated vs区别 visibilityOfElementLocated
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        driver.findElement(by).sendKeys(path);
    }


    /**
     * 此处的匹配对象 应该都是小写吧？
     * @param map
     */
    @Override
    public  void  click(HashMap<String,Object> map){
        super.click(map);
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


    @Override
    public  void  action(HashMap<String,Object> map){
        super.action(map);

        if(map.containsKey("action")){
            String action = map.get("action").toString().toLowerCase();
            if(action.equals("get")){
                driver.get(map.get("url").toString());
            }
        }

    }



}
