package com.test_web.pageobject0;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

public class MainPage {

    public static ChromeDriver driver;

    //todo:利用cookie登录 已完成

    //todo:启动浏览器
    public MainPage() {

        driver = new ChromeDriver();

        String url = "https://work.weixin.qq.com/wework_admin/frame";
        driver.get(url);
        driver.manage().deleteAllCookies();

        //todo:改从文件中读取 使用cookies登录企业微信
        loadCookies(driver);
        driver.get(url);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public ContactPage toContact() {

        //todo:登录后，进入通讯录页面；
        driver.findElement(By.cssSelector("#menu_contacts")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ContactPage();
    }

    public void loadCookies(WebDriver driver){
        try {
            FileReader fileReader = new FileReader("cookie.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, ";");
                String name= tokenizer.nextToken();
                String value= tokenizer.nextToken();
                String domain= tokenizer.nextToken();
                //string 转化为 date
                Date expiry =null;
                String dt= tokenizer.nextToken();
                if(!dt.equals("null")){
                    SimpleDateFormat sdf =new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                    sdf.parse(dt);
                }
                String path= tokenizer.nextToken();

                //string转化为boolean格式
                boolean secure= Boolean.parseBoolean(tokenizer.nextToken());

                /**
                 * Cookie(String name, String value, String domain, String path, Date expiry, boolean isSecure)
                 */
                Cookie cookie = new Cookie(name,value,domain,path,expiry,secure);
                driver.manage().addCookie(cookie);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


}