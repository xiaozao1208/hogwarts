package com.wechat.apiobject;

import com.wechat.utils.FakerUtils;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DepartmentApiObject {

    public static Response createDepartment(String createName,String createNameEn, String assessToken){
        String createBody ="{\n" +
                "   \"name\": \""+createName+"\",\n" +
                "   \"name_en\": \""+createNameEn+"\",\n" +
                "   \"parentid\": 1,\n" +
                "   \"order\": 3 \n + }";


        Response createResponse = given()
                .when()
                .contentType("application/json")
                .body(createBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token="+assessToken)
                .then()
                .log().body()
                .extract()
                .response();
        return createResponse;
    }


    public  static Response createDepartment(String assessToken){
        String createName="createName"+ FakerUtils.getTimeStamp();
        String createNameEn = "createNameEn"+FakerUtils.getTimeStamp();
        return createDepartment(createName,createNameEn,assessToken);
    }

    public static Response updateDepartment(String updateName,String updateNameEn,String assessToken,String departmentId) {

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
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token="+assessToken)
                .then()
                .log().body()
                .extract()
                .response();

        return updateResponse;
    }



    public  static Response deleteDepartment(String assessToken,String departmentId){
        Response deleteResponse = given()
                .when()
                .contentType("application/json")
                .param("access_token",assessToken)
                .param("id",departmentId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then()
                .log().body()
                .extract()
                .response();

        return deleteResponse;
    }

    public  static Response listDepartment(String assessToken,String departmentId){

        Response listResponse = given()
                .when()
                .contentType("application/json")
                .param("access_token",assessToken)
                .param("id",departmentId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then()
                .log().body()
                .extract()
                .response();

        return listResponse;
    }

}
