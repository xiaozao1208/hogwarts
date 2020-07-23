package com.wechat.testcases;

import com.sun.org.glassfish.gmbal.Description;
import com.wechat.utils.FakerUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 优化记录：
 *
 *   解决思路：
 *   保证每次请求的数据不重复---部门名称增加时间戳进行排重；（不能删除）
 *   保证每次请前后都对数据进行还原。
 *   ---每次方法执行前后都对历史数据进行清洗，确保每次执行脚本环境一致。（自己的环境，独享的，可以删除）
 */
public class Demo_03 {

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
        /**
         * 从createResponse 中取出id的值，并进行非空的判断；
         * 利用的是三元不等式
         */
        String departmentId = createResponse.path("id") !=null ?createResponse.path("id").toString() : null;

        assertEquals("0",createResponse.path("errcode").toString());

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
