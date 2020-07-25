package com.test_framwork_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;


/**
 * 对标了每个api object
 */
public class ApiObjectModel {
    /**
     * 与yaml文件中的名称一致
     */
    public String name;
    public HashMap<String,ApiObjectMethodModel> methods;


    /**
     * api object 支持从yaml中读取 加载yaml文件数据
     * @param path
     * @return
     */
    public static ApiObjectModel load(String path) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(new File(path),ApiObjectModel.class);
    }


    /**
     * 运行了
     * @param method
     */
    public void run(ApiObjectMethodModel method){
        method.run();
    }


    public void run(){
        //todo:
    }
}
