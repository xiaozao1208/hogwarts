package com.test_web.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class AlertTest extends BaseTest {


    @Test
    void  alertTest(){

        try {

            driver.get("https://www.runoob.com/try/try.php?filename=jqueryui-api-droppable");

            //切换到右边的页面   iframewrapper
            driver.switchTo().frame("iframeResult");

            //拖拽，从a到b
            Actions  actions = new Actions(driver);

            //perform不要忘记
            actions.dragAndDrop(driver.findElement(By.id("draggable")),driver.findElement(By.id("droppable"))).perform();
            Thread.sleep(2000);

            //处理alert，接受这个；
            driver.switchTo().alert().accept();

            //切换到其他的页面-点击运行页面
            driver.switchTo().parentFrame();

            System.out.println(driver.findElement(By.id("submitBTN")).getText());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }
}
