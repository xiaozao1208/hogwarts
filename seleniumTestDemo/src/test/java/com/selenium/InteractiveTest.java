package com.selenium;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class InteractiveTest {
    public static WebDriver driver;
//    public  static WebDriverWait wait;

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
    public  void clickTest(){
        driver.get("http://sahitest.com/demo/clicks.htm");
        Actions actions = new Actions(driver);

        actions.click(driver.findElement(By.xpath("//input[@value='click me']")));
        actions.doubleClick(driver.findElement(By.xpath("//input[@value='dbl click me']")));
        /*doubleClick 双击；contextClick 右击； click 单击*/
        actions.contextClick(driver.findElement(By.xpath("//input[@value='right click me']")));
        actions.perform();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 找到百度的设置,将光标悬停在设置上面
     */
    @Test
    public void moveTest(){

        try {

            driver.get("https://www.baidu.com");
            Actions actions = new Actions(driver);

            actions.moveToElement(driver.findElement(By.id("s-usersetting-top")));
            actions.perform();
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* 讲一个元素 拖拽到另一个元素的位置上*/
    @Test
    public void  dragTest()  {

        try {

            driver.get("http://sahitest.com/demo/dragDropMooTools.htm");
            Actions  actions =new Actions(driver);

            actions.dragAndDrop(driver.findElement(By.id("dragger")),
                    driver.findElement(By.xpath("//body/div[2]"))).perform();

            Thread.sleep(3000);

            actions.dragAndDrop(driver.findElement(By.id("dragger")),
                    driver.findElement(By.xpath("//body/div[3]"))).perform();

            Thread.sleep(3000);

            actions.dragAndDrop(driver.findElement(By.id("dragger")),
                    driver.findElement(By.xpath("//body/div[4]"))).perform();

            Thread.sleep(3000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /* 使用键盘操作   谷歌浏览器不支持，火狐支持*/
    @Test
    public void keyBoardTest(){

        try {

            driver.get("http://sahitest.com/demo/label.htm");
            driver.findElements(By.xpath("//input[@type='textbox]")).get(0).sendKeys("admin");
            Thread.sleep(5000);

            Actions actions = new Actions(driver);
            actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
            actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
            actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();

            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    /**
     * 页面的滚动 scroll
     */
    @Test
    public void scrollTest() {

        try {

            driver.get("https://www.baidu.com");
            driver.findElement(By.id("kw")).sendKeys("霍格沃兹测试学院");

            TouchActions actions = new TouchActions(driver);
            actions.click(driver.findElement(By.id("su")));


           Thread.sleep(2000);

            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//            js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
            Thread.sleep(4000);

            driver.findElement(By.xpath("//a[@class='n']")).click();

            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }





}
