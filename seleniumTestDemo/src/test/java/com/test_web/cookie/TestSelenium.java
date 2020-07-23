package com.test_web.cookie;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * cookie登录，其他免登录
 * 第一步：复用浏览器，使用saveCookie方法获取cookie;
 * 第二步：不复用浏览器，使用loadCookie方法调用获取的cookie.txt;
 * 第三步：进行重新登录；（如果cookie失效了，重新获取新的cookie）
 *
 */

public class TestSelenium {

    private WebDriver driver;

    /**
     * 首次登录获取cookie
     */
    @Test
    public  void  getCookies() {

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        driver = new ChromeDriver(options);
        //利用chromedriver 驱动 chrome

        driver.get("https://work.weixin.qq.com/wework_admin/frame");
        saveCookies(driver);
    }

    /**
     * 后面登录读取cookie
     //         */
    @Test
    public void  readCookies(){

        driver = new ChromeDriver();
        //利用chromedriver 驱动 chrome

        driver.get("https://work.weixin.qq.com/wework_admin/frame");
        loadCookies(driver);
        driver.get("https://work.weixin.qq.com/wework_admin/frame");
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    /**
     * 只取一个cookies进行处理
     * 文件流--文件流的读写  消耗资源较大
     */
    public  void saveCookies(WebDriver driver){
        try {
            FileWriter fileWriter = new FileWriter("cookie.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);


            for (Cookie cookie:driver.manage().getCookies()) {
                //获取cookie的详细内容
                bufferedWriter.write(
                        cookie.getName() + ";" +
                                cookie.getValue() + ";" +
                                cookie.getDomain() + ";" +
                                cookie.getExpiry() + ";" +
                                cookie.getPath() + ";" +
                                cookie.isSecure());
                bufferedWriter.newLine(); //添加换行
            }

            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 读取cookie文件信息
     * StringTokenizer 分词器 对行进行分词操作
     *
     */
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

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}



