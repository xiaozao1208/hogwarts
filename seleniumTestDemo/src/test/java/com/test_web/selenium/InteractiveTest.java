package com.test_web.selenium;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;

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


    /**
     * 元素的常见的操作事件（右键点击，页面滑动，表单操作等）
     */
    @Test
    public  void clickTest(){
        driver.get("http://sahitest.com/demo/clicks.htm");
        Actions actions = new Actions(driver);

        // click 单击
        actions.click(driver.findElement(By.xpath("//input[@value='click me']")));

        //doubleClick 双击；
        actions.doubleClick(driver.findElement(By.xpath("//input[@value='dbl click me']")));

        //contextClick 右击；
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
            driver.manage().window().maximize();
            Actions actions = new Actions(driver);

            actions.moveToElement(driver.findElement(By.id("s-usersetting-top")));
            actions.perform();
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将一个元素 拖拽到另一个元素的位置上
     */
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

    /**
     * 使用键盘操作   谷歌浏览器不支持，火狐支持
     */
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
     * 页面的滚动 scroll,页面滑动，需要使用js;
     */
    @Test
    public void scrollTest() {

        try {
            //输入百度地址，进行搜索“霍格沃兹测试学院”
            driver.get("https://www.baidu.com");
            driver.findElement(By.id("kw")).sendKeys("霍格沃兹测试学院");

            //点击百度一下
            TouchActions actions = new TouchActions(driver);
            actions.click(driver.findElement(By.id("su")));


           Thread.sleep(2000);

           //将driver转化成js，对页面进行滑动，滑动到底部
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//            js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
            Thread.sleep(4000);

            //点击页码的下一页；
            driver.findElement(By.xpath("//a[@class='n']")).click();

            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }





}
