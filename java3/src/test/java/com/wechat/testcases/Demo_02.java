package com.wechat.testcases;

import com.sun.org.glassfish.gmbal.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Demo_02 {
    static String  assess_token;
    static String  departmentId;

    /**
     * 第二版优化日志：
     * 1.将第一步创建的departmentID 参数化取出来；
     * 2. 修改和删除，必须需要departmentId；如果 四个必须执行，耦合性比较高，这个对于后期的并发测试，单独测试不好；
     *   建议每个功能做成独立的模块，耦合性降低；
     *
     *
     *
     *   老师的优化日志：
     *   1.增加了入参实时获取的逻辑；
     *   2.增加了脚本的独立性改造；
     *
     *   解决思路：
     *   因为入参写死了，导致第二次请求数据重复，造成报错
     *
     *   解决思路：
     *   保证每次请求的数据不重复---部门名称增加时间戳进行排重；（不能删除）
     *   保证每次请前后都对数据进行还原。
     *   ---每次方法执行前后都对历史数据进行清洗，确保每次执行脚本环境一致。（自己的环境，独享的，可以删除）
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
                "   \"name\": \"上海研发中心11\",\n" +
                "   \"name_en\": \"ILSH11\",\n" +
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
        departmentId = createResponse.path("id") !=null ?createResponse.path("id").toString() : null;
        assertEquals("0",createResponse.path("errcode").toString());

    }


    @Test
    @Description("更新部门信息")
    @Order(2)
    void updateDepartment(){

        String createBody ="{\n" +
                "   \"name\": \"上海研发中心11\",\n" +
                "   \"name_en\": \"ILSH11\",\n" +
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
        departmentId = createResponse.path("id") !=null ?createResponse.path("id").toString() : null;

        String updateBody ="{\n" +
                "   \"id\": "+departmentId+" ,\n" +
                "   \"name\": \"上海研发中心修改12\",\n" +
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
