package com.wechat.testcases;

import com.sun.org.glassfish.gmbal.Description;
import com.wechat.apiobject.DepartmentApiObject;
import com.wechat.apiobject.TokenHelper;
import com.wechat.task.EvnTask;
import com.wechat.utils.FakerUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 优化记录：
 *  1.增加了入参实时获取的逻辑；
 *  2.增加了脚本的独立性改造；
 *  3.通过添加 evnClear方法解决脚本无法重复运行的问题；
 *  解决代码重复的问题，对代码进行封装
 *  4.对脚本进行了分层，减少了重复代码；减少了维护成本；
 */
public class Demo_04 {

    private static final Logger logger = LoggerFactory.getLogger(Demo_04.class);
    static String  assessToken;
    static String  departmentId;

    @BeforeAll
    static void  getAssessToken(){
        assessToken = TokenHelper.getAssessToken();
    }

    /**
     * 清理数据的方法
     * 先查询出部门的所有列表，然后全部删除；
     * （可能会删除别人的数据----对自己创建的数据进行标识，只删除自己创建的数据；）
     * 开始之前和结束之后，进行数据清理；---增加脚本的健壮性
     */
    @BeforeEach
    @AfterEach
    void evnClear(){
        EvnTask.evnClear(assessToken);
    }

    @Test
    @Description("创建部门")
    void createDepartment(){

        String createName="createName"+ FakerUtils.getTimeStamp();
        String createNameEn = "createNameEn"+FakerUtils.getTimeStamp();
        Response createResponse = DepartmentApiObject.createDepartment(createName,createNameEn,assessToken);

        String departmentId = createResponse.path("id") !=null ?createResponse.path("id").toString() : null;

    }



    @Test
    @Description("更新部门信息")
    void updateDepartment(){

        Response createResponse = DepartmentApiObject.createDepartment(assessToken);
        String departmentId = createResponse.path("id") !=null ?createResponse.path("id").toString() : null;

        String updateName="updateName"+FakerUtils.getTimeStamp();
        String updateNameEn ="updateNameEn"+FakerUtils.getTimeStamp();
        Response updateResponse = DepartmentApiObject.updateDepartment(updateName,updateNameEn,assessToken,departmentId);

    }

    @Test
    @Description("删除部门")
    void  deleteDepartment(){

        Response createResponse = DepartmentApiObject.createDepartment(assessToken);
        String departmentId = createResponse.path("id") !=null ?createResponse.path("id").toString() : null;

        Response deleteResponse =DepartmentApiObject.deleteDepartment(assessToken,departmentId);
    }

    @Test
    @Description("获取部分列表")
    void  listDepartment(){

        Response createResponse = DepartmentApiObject.createDepartment(assessToken);
        String departmentId = createResponse.path("id") !=null ?createResponse.path("id").toString() : null;

        Response listResponse =DepartmentApiObject.listDepartment(assessToken,departmentId);

    }

}
