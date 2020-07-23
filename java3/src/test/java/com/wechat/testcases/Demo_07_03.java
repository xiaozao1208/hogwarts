package com.wechat.testcases;

import com.sun.org.glassfish.gmbal.Description;
import com.wechat.apiobject.DepartmentApiObject;
import com.wechat.apiobject.TokenHelper;
import com.wechat.task.EvnTask;
import com.wechat.utils.FakerUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

/**
 *
 *
 *
 */
public class Demo_07_03 {

    private static final Logger logger = LoggerFactory.getLogger(Demo_07_03.class);
    static String  assessToken;
    static String  departmentId;

    @BeforeAll
    static void  getAssessToken(){
        assessToken = TokenHelper.getAssessToken();
    }

    /**
     * 创建部门和更新部门同时进行（各10个并发）
     *  @Description("创建部门")
     *  @RepeatedTest(10)
     *  @Execution(CONCURRENT)  这三个都要加；
     *
     *  String createName="createName"+ FakerUtils.getTimeStamp();
     *  多线程并发，有可能出现时间重复的问题。
     *  解决方法：
     *  String backentStr = Thread.currentThread().getId()+FakerUtils.getTimeStamp();
     *  不会出现锁表，全部执行成功。
     *
     */

    @Description("创建部门")
    @RepeatedTest(10)
    @Execution(CONCURRENT)
    void createDepartment(){

        String backentStr = Thread.currentThread().getId()+FakerUtils.getTimeStamp();

        String createName="createName"+backentStr;
        String createNameEn = "createNameEn"+backentStr;
        Response createResponse = DepartmentApiObject.createDepartment(createName,createNameEn,assessToken);

         departmentId = createResponse.path("id") !=null ?createResponse.path("id").toString() : null;
        assertEquals("0",createResponse.path("errcode").toString());
    }



    @Description("更新部门信息")
    @RepeatedTest(10)
    @Execution(CONCURRENT)
    void updateDepartment(){

        String backentStr = Thread.currentThread().getId()+FakerUtils.getTimeStamp();
        String createName="createName"+ backentStr;
        String createNameEn = "createNameEn"+backentStr;
        Response createResponse = DepartmentApiObject.createDepartment(assessToken);
         departmentId = createResponse.path("id") !=null ?createResponse.path("id").toString() : null;

        String updateName="updateName"+backentStr;
        String updateNameEn ="updateNameEn"+backentStr;
        Response updateResponse = DepartmentApiObject.updateDepartment(updateName,updateNameEn,assessToken,departmentId);

    }



}
