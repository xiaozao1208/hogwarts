package com.test_framwork_service;

import java.util.HashMap;
import java.util.List;

/**
 * step1:加载测试用例
 * 1.读取测试用例的yaml文件，并提供执行
 */
public class ApiTestCaseModel {

     public String name;
     public  String description;
     public  List<HashMap<String,Object>> steps;


    /**
     * 加载一个yaml文件，并转成 测试用例的模型类 ？？
     * @return
     */
    public static ApiTestCaseModel load(String path){

     }


    /**
     * 根据
     */
    public void run(String name,String action){

     }


}
