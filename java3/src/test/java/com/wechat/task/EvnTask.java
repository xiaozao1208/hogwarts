package com.wechat.task;

import com.wechat.apiobject.DepartmentApiObject;
import io.restassured.response.Response;

import java.util.ArrayList;

/**
 * 环境任务管理
 */
public class EvnTask {

    public static void evnClear(String assessToken){

        Response listResponse = DepartmentApiObject.listDepartment(assessToken,"");

        ArrayList<Integer> departmentIdList = listResponse.path("department.id");
        for(int departmentId:departmentIdList){

            if(1==departmentId){
                continue;
            }
            //departmentId 是int 转化为String
             DepartmentApiObject.deleteDepartment(assessToken,departmentId+"");
        }
    }
}
