package com.test_web.pageobject;


import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;


public class ContactPage  extends WebBasePage {

    By addMember =By.linkText("添加成员");
    By username= By.xpath("//*[@id='username']");
    By delete = By.linkText("删除");

    public ContactPage(RemoteWebDriver driver) {
        this.driver= driver;
    }

    /**
     *此处等待时间 坑比较多； assert
     */
    public ContactPage addMember(String username,String acctid,String mobile){

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


    public ContactPage search(String keyword){

        sendKeys(By.id("memberSearchInput"),keyword);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(delete));

        return this;
    }

    //这个地方的定位元素不一定是不变的；
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
//        System.out.println(path.getPath());
//        String path_utf;


        click(By.cssSelector(".ww_operationBar:nth-child(1) .ww_btn_PartDropdown_left"));
        click(By.linkText("文件导入"));
        upload(By.name("file"),"D:\\ideaproject\\hogwarts\\seleniumTestDemo\\src\\main\\resources\\通讯录批量导入模板.xlsx");
        click(By.linkText("导入"));
        click(By.linkText("完成"));

        return  this;

    }

}
