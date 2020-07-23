package com.test_web.selenium;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    public static WebDriver driver;

    @BeforeAll
    public static void  setUp(){

        String browserName = System.getenv("browser");
        if( "chrome".equals(browserName)){
            System.setProperty("webdriver.chrome.driver","D:/ideaproject/hogwarts/seleniumTestDemo/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }else if("firefox".equals(browserName)){
            System.setProperty("webdriver.gecko.driver","XXXX");
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }else if("safari".equals(browserName)){
            driver = new SafariDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } else {
            System.setProperty("webdriver.chrome.driver","D:/ideaproject/hogwarts/seleniumTestDemo/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }

    }


    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
