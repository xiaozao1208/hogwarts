package com.test_framework;

import com.test_app.snowball.page.AppBasePage;
import com.test_web.wework.page.WebBasePage;

public class UIAutoFactory {

    public static WebBasePage create(String driverName){
        if(driverName.equals("web") || driverName.equals("selenium")){
            return new WebBasePage();
        }

        if(driverName == "app" || driverName =="appium"){
            return new AppBasePage();
        }

        return null;
    }

}
