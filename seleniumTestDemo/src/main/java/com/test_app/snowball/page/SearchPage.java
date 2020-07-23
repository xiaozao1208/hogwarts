package com.test_app.snowball.page;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SearchPage {

    private AndroidDriver driver;
    private By nameLocator =By.id("name");


    public SearchPage(){

    }

    public  SearchPage(AndroidDriver driver){
        this.driver =driver;
    }


    //需要吗？--需要
    public List<String> getSearchList(){

        List<String> nameList =  new ArrayList<>();

        for( Object element : driver.findElements(nameLocator) ){
            nameList.add(((WebElement)element).getText());
        }
        //todo:stream lamda 优化
        return nameList;
    }



    public SearchPage search(String Keyword){

        MobileElement el2 = (MobileElement) driver.findElementById("com.xueqiu.android:id/search_input_text");
        el2.click();
        el2.sendKeys(Keyword);

//        MobileElement ele5 = (MobileElement)driver.findElement(nameLocator);
//        ele5.sendKeys(Keyword);
        return this;
    }


    public Double getPrice(){

        //driver.findElement(nameLocator).click();
        //todo:独立一个独立的po方法

        MobileElement el3 = (MobileElement) driver.findElement(nameLocator);
        el3.click();

        return Double.valueOf(driver.findElement(By.id("current_price")).getText());
    }
}
