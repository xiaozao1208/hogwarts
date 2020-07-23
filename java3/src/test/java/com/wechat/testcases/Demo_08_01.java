package com.wechat.testcases;

import com.sun.org.glassfish.gmbal.Description;
import com.wechat.apiobject.DepartmentApiObject;
import com.wechat.apiobject.TokenHelper;
import com.wechat.task.EvnTask;
import com.wechat.utils.FakerUtils;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


@Epic("Epic企业微信接口测试用例22222")
@Feature("Feature部门功能测试")
public class Demo_08_01 {

        private static final Logger logger = LoggerFactory.getLogger(Demo_08_01.class);
        static String  assessToken;
        static String  departmentId;

        @BeforeAll
        static void  getAssessToken(){
            assessToken = TokenHelper.getAssessToken();
        }

        @BeforeEach
        @AfterEach
        void evnClear(){
            EvnTask.evnClear(assessToken);
        }


        @Description("Description 创建部门创建部门创建部门创建部门")
        @Story("Story 创建部门测试")
        @DisplayName("DisplayName创建部门")
        @Severity(SeverityLevel.BLOCKER)
        @Issue("123")
        @TmsLink("test_001")
        @CsvFileSource(resources = "/createData.csv")
        @ParameterizedTest
        void createDepartment(String createName,String createNameEn,String returnCode){
            Response createResponse = DepartmentApiObject.createDepartment(createName,createNameEn,assessToken);

             departmentId = createResponse.path("id") !=null ?createResponse.path("id").toString() : null;
            assertEquals(returnCode,createResponse.path("errcode").toString());
        }



        @Test
        @Description("Description 更新部门信息更新部门信息更新部门信息")
        @Story("Story 更新部门信息")
        @DisplayName("DisplayName更新部门信息")
        @Severity(SeverityLevel.BLOCKER)
        @Issue("1")
        @TmsLink("test_002")
        void updateDepartment(){

            Response createResponse = DepartmentApiObject.createDepartment(assessToken);
            String departmentId = createResponse.path("id") !=null ?createResponse.path("id").toString() : null;

            String updateName="updateName"+ FakerUtils.getTimeStamp();
            String updateNameEn ="updateNameEn"+FakerUtils.getTimeStamp();
            Response updateResponse = DepartmentApiObject.updateDepartment(updateName,updateNameEn,assessToken,departmentId);

        }

        @Test
        @Description("删除部门")
        void  deleteDepartment(){

            Response createResponse = DepartmentApiObject.createDepartment(assessToken);
             departmentId = createResponse.path("id") !=null ?createResponse.path("id").toString() : null;

            Response deleteResponse =DepartmentApiObject.deleteDepartment(assessToken,departmentId);
        }

        @Test
        @Description("获取部分列表")
        void  listDepartment(){

            String createName="createName"+ FakerUtils.getTimeStamp();
            String createNameEn = "createNameEn"+FakerUtils.getTimeStamp();

            Response createResponse = DepartmentApiObject.createDepartment(assessToken);
             departmentId = createResponse.path("id") !=null ?createResponse.path("id").toString() : null;

            Response listResponse =DepartmentApiObject.listDepartment(assessToken,departmentId);
/**
 * 软断言---一个断言失败，其他断言仍然会继续，会输出所有断言的结果；
 * 脚本的容错性比较强；
 *
 */
//        assertEquals("0",listResponse.path("errcode").toString());
//        assertEquals(departmentId,listResponse.path("department.id[0]").toString());
//        assertEquals(createName,listResponse.path("department.name[0]").toString());
//        assertEquals(createNameEn,listResponse.path("department.name_en[0]").toString());

            assertAll("返回值校验",
                    () ->assertEquals("0"+"xx",listResponse.path("errcode").toString()),
                    () ->assertEquals(departmentId+"xx",listResponse.path("department.id[0]").toString()),
                    () ->assertEquals(createName+"xx",listResponse.path("department.name[0]").toString()),
                    () ->assertEquals(createNameEn+"xx",listResponse.path("department.name_en[0]").toString())
            );

        }

}


