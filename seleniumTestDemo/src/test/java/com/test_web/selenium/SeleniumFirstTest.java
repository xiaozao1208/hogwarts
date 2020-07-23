package com.test_web.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SeleniumFirstTest {

   public  static WebDriver driver;

   @Test
   public  void  startSeleniumTest()  {

      WebDriver driver = new ChromeDriver();

      //这个可以不写；
      //路径不要写在main/test目录下，要写在大目录下；
      System.setProperty("webdriver.chrome.driver","D:/ideaproject/hogwarts/seleniumTestDemo/chromedriver.exe");

      driver.get("https://www.baidu.com");
      driver.findElement(By.id("kw")).sendKeys("姜子牙");
      driver.findElement(By.id("su")).click();

      driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);

      driver.quit();
   }


}
