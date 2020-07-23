package com.test_framework;

import com.test_app.snowball.page.AppBasePage;
import com.test_web.pageobject.WebBasePage;

public class Factory  extends BasePage{

    public BasePage create(String driverName){
        if(driverName == "web" || driverName == "selenium"){
            return new WebBasePage();
        }

        if(driverName == "app" || driverName =="appium"){
            return new AppBasePage();
        }

        return null;
    }

}
