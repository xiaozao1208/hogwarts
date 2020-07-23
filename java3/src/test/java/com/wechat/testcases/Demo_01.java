package com.wechat.testcases;

import com.sun.org.glassfish.gmbal.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 部门相关的测试
 * 1.get 带参数的请求和使用 log().body()打印日志
 * 2.post 带参数
 *
 * 主要内容：
 * 1.获取assess_token
 * 2. 创建部门
 * 3.修改部门
 * 4.删除部门
 * 5.部门列表查询
 *
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Demo_01 {

    static String  assess_token;

    /**
     * 1.get 带参数的请求和使用 log().body()打印日志
     * corpid
     * corpsecret  管理工具-Secret
     */
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
     * 可通过API接口或第三方应用同步通讯录  获取API编辑权限  管理工具-通讯录同步-权限修改
     */
    @Test
    @Description("创建部门")
    @Order(1)
    void createDepartment(){

        String createBody ="{\n" +
                "   \"name\": \"上海研发中心\",\n" +
                "   \"name_en\": \"ILSH\",\n" +
                "   \"parentid\": 1,\n" +
                "   \"order\": 3 \n + }";


       Response createrResponse = given()
                .when()
                .contentType("application/json")
                .body(createBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token="+assess_token)
                .then()
                .log().body()
                .extract()
                .response();

       assertEquals("0",createrResponse.path("errcode").toString());

    }


    @Test
    @Description("更新部门信息")
    @Order(2)
    void updateDepartment(){

        String updateBody ="{\n" +
                "   \"id\": 4 ,\n" +
                "   \"name\": \"上海研发中心修改\",\n" +
                "   \"name_en\": \"ILSH\",\n" +
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

        Response delereResponse = given()
                .when()
                .contentType("application/json")
                .param("access_token",assess_token)
                .param("id",4)
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
