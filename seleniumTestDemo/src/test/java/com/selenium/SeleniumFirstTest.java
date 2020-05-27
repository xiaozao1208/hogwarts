package com.selenium;

import com.google.common.annotations.VisibleForTesting;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class SeleniumFirstTest {
   static WebDriver driver;
   @Test
   public  void  test1()  {
      WebDriver   driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
      //System.setProperty("webdriver.chrome.driver","D:/ideaproject/hogwarts/seleniumTestDemo/src/main/resources/chromedriver.exe");
      //这个可以不写；

      driver.get("https://www.baidu.com");
      // 点击页面右侧的设置
      Actions action = new Actions(driver);


      try {
         action.moveToElement(driver.findElement(By.id("s-usersetting-top")));
         action.perform();
         Thread.sleep(3000);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }

//      driver.findElement(By.id("kw")).sendKeys("姜子牙");
//      driver.findElement(By.id("su")).click();



      driver.quit();
   }


}
