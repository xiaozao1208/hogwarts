package com.selenium;

import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BaseTest {

    public static WebDriver driver;

    @BeforeAll
    public static void  setUp(){

        String browserName = System.getenv("browser");
        if( "chrome".equals(browserName)){
            System.setProperty("webdriver.chrome.driver","D:/ideaproject/hogwarts/seleniumTestDemo/chromedriver.exe");
            driver = new ChromeDriver();
        }else if("firefox".equals(browserName)){
            System.setProperty("webdriver.gecko.driver","//");
            driver = new FirefoxDriver();
        }else if("safari".equals(browserName)){
            driver = new SafariDriver();
        }


    }
}
