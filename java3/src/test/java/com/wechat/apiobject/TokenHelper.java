package com.wechat.apiobject;

import static io.restassured.RestAssured.given;

/**
 * token工具类
 */
public class TokenHelper {

    public static String getAssessToken(){
        String assessToken =  given()
                .param("corpid","wwdbfbbb03080d3448")
                .param("corpsecret","N0LbJ1_ZzTgq_MV7zUQl1s99QJ8QGkStqWZMxjhDqfU")
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .log().body()
                .extract()
                .response()
                .path("access_token");

        return assessToken;
    }
}
