package com.test_services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.ref.Reference;

import static io.restassured.RestAssured.given;

public class GetTest {

    /**
     * 获取access_token
     */
    String access_token;

    @BeforeEach
    void getMethod(){

           access_token =  given()
                   .params("corpid","wwdbfbbb03080d3448","corpsecret","5p-UoDGgDT1BX9WCtDpjL5c2eOjmb-YajwIGs5oVSk8")
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .statusCode(200)
                .extract().response().path("access_token");

    }


    @Test
    void postTest(){

        given()
                .contentType("application/json;charset=utf-8")
                .body("{\n" +
                        "   \"touser\" : \"@all\",\n" +
                        "   \"msgtype\" : \"text\",\n" +
                        "   \"agentid\" : 1000002,\n" +
                        "   \"text\" : {\n" +
                        "       \"content\" : \"你的快递已到，请携带工卡前往邮件中心领取。\\n出发前可查看<a href=\\\"http://work.weixin.qq.com\\\">邮件中心视频实况</a>，聪明避开排队。\"\n" +
                        "   },\n" +
                        "   \"safe\":0\n" +
                        "}")
                .queryParam("access_token",access_token)
                .post("https://qyapi.weixin.qq.com/cgi-bin/message/send")
                .then()
                .log().all();

    }


}
