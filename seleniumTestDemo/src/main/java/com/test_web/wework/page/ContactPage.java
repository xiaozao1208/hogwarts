package com.test_web.wework.page;


import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.time.Duration;

/**
 * 企业微信--通讯录页面的基类 ContactPage
 * 一个页面一个基类
 */
public class ContactPage  extends WebBasePage {

    By addMember =By.linkText("添加成员");
    By username= By.xpath("//*[@id='username']");
    By delete = By.linkText("删除");

    public ContactPage(RemoteWebDriver driver) {
//        this.driver= driver;
        super(driver);
    }

    /**
     *此处等待时间 坑比较多； assert
     * 通讯录-添加成员的方法
     */
    public ContactPage addMember(String username,String acctid,String mobile){

        //方法1：强制等待
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        //方法二：显示等待
//        new WebDriverWait(driver, 10)
//                .until(ExpectedConditions.visibilityOfElementLocated(addMember));
//        //todo: 就算可点击，仍然有一定的概率是点击不成功的
//        new WebDriverWait(driver, 10)
//                .until(ExpectedConditions.elementToBeClickable(addMember));


        //方法三：推荐方法  进行条件判断
        /**
         * 用户名没有找到会一直点击添加成员；
         */
        while(driver.findElements(addMember).size() > 0) {
            click(addMember);
        }

        sendKeys(this.username,username);
        sendKeys(By.name("acctid"),acctid);
        sendKeys(By.name("mobile"),mobile);
        //点击保存
        click(By.cssSelector(".js_btn_save"));
        return this;
    }


    /**
     * 通讯录页面--搜索通讯录成员
     * @param keyword
     * @return
     */
    public ContactPage search(String keyword){

        sendKeys(By.id("memberSearchInput"),keyword);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(delete));

        return this;
    }

    //这个地方的定位元素不一定是不变的；
    //获取成员的姓名
    public  String  getUserName(){
        return driver.findElement(By.cssSelector(".member_display_cover_detail_name")).getText();
    }



    /**
     * 删除---确认---关闭
     */
    public ContactPage delete(){

        click(delete);
        click(By.linkText("确认"));
        click(By.id("clearMemberSearchInput"));
        return  this;
    }


    public ContactPage  importFromFile(URL path){
        //todo:中文名 设置一下；
        System.out.println(path.getPath());
        String path_utf="";
        try {
            path_utf = URLDecoder.decode(path.getFile(),"UTF-8");
            System.out.println(path_utf);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        click(By.cssSelector(".ww_operationBar:nth-child(1) .ww_btn_PartDropdown_left"));
        click(By.linkText("文件导入"));
        upload(By.name("file"),path_utf);
        click(By.linkText("导入"));
        click(By.linkText("完成"));

        return  this;

    }

}
