package com.pageobject;

public class MainPage {

    //todo:利用cookie登录；  未完成

    //todo:启动浏览器
    public  MainPage(){

    }


    public ContactPage toContact(){
        return new ContactPage();
    }


}
