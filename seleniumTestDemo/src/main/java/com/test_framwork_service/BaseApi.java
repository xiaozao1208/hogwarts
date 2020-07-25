package com.test_framwork_service;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * step2:BaseApi
 * 类似于BasePage
 *
 */
public class BaseApi {

    /**
     * 保存了所有的Api对象
     */
   List<ApiObjectModel> apis = new ArrayList<>();


    /**
     * 加载所有的api object 对象，并保存到一个列表中
     * @param dir
     */
   public void load(String dir){
       Arrays.stream(new File(dir).list(new FilenameFilter() {
           @Override
           public boolean accept(File dir, String name) {
               //todo:filter
               return true;
           }
       })).forEach(path->apis.add(ApiObjectModel.load(dir+"/"+path)));
   }


    /**
     *
     * @param aa
     * @param bb
     */
    public void run(String aa,String bb){

        apis.stream();
    }
}
