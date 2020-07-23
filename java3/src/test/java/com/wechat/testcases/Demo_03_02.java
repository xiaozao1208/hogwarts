package com.wechat.testcases;

import com.sun.org.glassfish.gmbal.Description;
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
 *   保证每次请前后都对数据进行还原。---每次方法执行前后都对历史数据进行清洗，确保每次执行脚本环境一致。
 *   （环境独享，删除数据不会对别人的数据有影响；）
 */
public class Demo_03_02 {

    private static final Logger logger = LoggerFactory.getLogger(Demo_03_02.class);
    static String  assess_token;

    @BeforeAll
    static void  getAssessToken(){
        assess_token =  given()
                .param("corpid","wwdbfbbb03080d3448")
                .param("corpsecret","N0LbJ1_ZzTgq_MV7zUQl1s99QJ8QGkStqWZMxjhDqfU")
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .log().body()
                .extract()
                .response()
                .path("access_token");
    }

    /**
     * 清理数据的方法
     * 先查询出部门的所有列表，然后全部删除；
     * （可能会删除别人的数据----对自己创建的数据进行标识，只删除自己创建的数据；）
     *
     * 开始之前和结束之后，进行数据清理；---增加脚本的健壮性
     */
    @BeforeEach
    @AfterEach
    void evnClear(){
        Response listResponse = given()
                .when()
                .contentType("application/json")
                .param("access_token",assess_token)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then()
                .log().body()
                .extract()
                .response();

        ArrayList<Integer> departmentIdList = listResponse.path("department.id");
        for(int departmentId:departmentIdList){

            if(1==departmentId){
                continue;
            }
            Response delereResponse = given()
                    .when()
                    .contentType("application/json")
                    .param("access_token",assess_token)
                    .param("id",departmentId)
                    .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                    .then()
                    .log().body()
                    .extract()
                    .response();
        }
    }

    @Test
    @Description("创建部门")
    @Order(1)
    void createDepartment(){

        String createName="createName"+ FakerUtils.getTimeStamp();
        String createNameEn = "createNameEn"+FakerUtils.getTimeStamp();

        String createBody ="{\n" +
                "   \"name\": \""+createName+"\",\n" +
                "   \"name_en\": \""+createNameEn+"\",\n" +
                "   \"parentid\": 1,\n" +
                "   \"order\": 3 \n + }";


        Response createResponse = given()
                .when()
                .contentType("application/json")
                .body(createBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token="+assess_token)
                .then()
                .log().body()
                .extract()
                .response();
        String departmentId = createResponse.path("id") !=null ?createResponse.path("id").toString() : null;

    }



    @Test
    @Description("更新部门信息")
    @Order(2)
    void updateDepartment(){

        String createName="createName"+ FakerUtils.getTimeStamp();
        String createNameEn = "createNameEn"+FakerUtils.getTimeStamp();

        String createBody ="{\n" +
                "   \"name\": \""+createName+"\",\n" +
                "   \"name_en\": \""+createNameEn+"\",\n" +
                "   \"parentid\": 1,\n" +
                "   \"order\": 3 \n + }";


        Response createResponse = given()
                .when()
                .contentType("application/json")
                .body(createBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token="+assess_token)
                .then()
                .log().body()
                .extract()
                .response();
        /**
         * 从createResponse 中取出id的值，并进行非空的判断；
         * 利用的是三元不等式
         */
        String departmentId = createResponse.path("id") !=null ?createResponse.path("id").toString() : null;

        String updateName="updateName"+FakerUtils.getTimeStamp();
        String updateNameEn ="updateNameEn"+FakerUtils.getTimeStamp();

        String updateBody ="{\n" +
                "   \"id\": "+departmentId+" ,\n" +
                "   \"name\": \""+updateName+"\",\n" +
                "   \"name_en\": \""+updateNameEn+"\",\n" +
                "   \"parentid\": 1,\n" +
                "   \"order\": 3 \n + }";


        Response updateResponse = given()
                .when()
                .contentType("application/json")
                .body(updateBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token="+assess_token)
                .then()
                .log().body()
                .extract()
                .response();
        assertEquals("0",updateResponse.path("errcode").toString());
    }

    @Test
    @Description("删除部门")
    @Order(4)
    void  deleteDepartment(){

        String createName="createName"+ FakerUtils.getTimeStamp();
        String createNameEn = "createNameEn"+FakerUtils.getTimeStamp();

        String createBody ="{\n" +
                "   \"name\": \""+createName+"\",\n" +
                "   \"name_en\": \""+createNameEn+"\",\n" +
                "   \"parentid\": 1,\n" +
                "   \"order\": 3 \n + }";


        Response createResponse = given()
                .when()
                .contentType("application/json")
                .body(createBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token="+assess_token)
                .then()
                .log().body()
                .extract()
                .response();
        /**
         * 从createResponse 中取出id的值，并进行非空的判断；
         * 利用的是三元不等式
         */
        String departmentId = createResponse.path("id") !=null ?createResponse.path("id").toString() : null;

        Response delereResponse = given()
                .when()
                .contentType("application/json")
                .param("access_token",assess_token)
                .param("id",departmentId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then()
                .log().body()
                .extract()
                .response();

        assertEquals("0",delereResponse.path("errcode").toString());
    }

    @Test
    @Description("获取部分列表")
    @Order(3)
    void  listDepartment(){

        Response listResponse = given()
                .when()
                .contentType("application/json")
                .param("access_token",assess_token)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then()
                .log().body()
                .extract()
                .response();

        assertEquals("0",listResponse.path("errcode").toString());

    }

}
