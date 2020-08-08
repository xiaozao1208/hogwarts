package com.test_framework;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.test_framework.UIAuto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//自动化领域的建模
public class BasePage {
    List<PageObjectModel> pages = new ArrayList<>();

    public  void  click(HashMap<String,Object> map){
        System.out.println("click");
        System.out.println(map);
//        driver.findElement(by).click();
    }

    public  void  find(){

    }

    public  void  sendKeys(HashMap<String,Object> map){
        System.out.println("sendkyes");
        System.out.println(map);
    }

    public  void  getText(){


    }

    public void  action(HashMap<String,Object> map){
        System.out.println("action");
        System.out.println(map);
    }


    public  void run(UIAuto uiAuto){
        uiAuto.steps.stream().forEach(m ->{
//            if(m.keySet().contains("click")){
//                click((HashMap<String,Object>)  m.get("click"));
//            }

            if(m.containsKey("click")){
                HashMap<String, Object> by = (HashMap<String,Object>) m.get("click");
                click(by);
            }

            if(m.containsKey("sendKeys")){
                sendKeys(m);
            }

            if(m.containsKey("action")){
                action(m);
            }
        });
    }


    public UIAuto load(String  path){

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        UIAuto uiauto = null;
        try {
            uiauto = mapper.readValue(
                        BasePage.class.getResourceAsStream(path),
                        UIAuto.class
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        return uiauto;

    }
}
