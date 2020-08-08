package com.test_service.mock;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseBuilder;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;

import java.util.Base64;

public class ExampleTransformer {

    @BeforeAll
    static void beforeAll(){
        RestAssured.filters((req,res,ctx)->{
            if(req.getURI().contains("encode.json")){
                System.out.println(req.getURI());

                //返回的Response 不具备set方法，无法修改body
                Response originResponse = ctx.next(req,res);

                //ResponseBuilder的作用主要是在Response的基础上建设出来一个新的可以修复的body对象
                ResponseBuilder responseBuilder = new ResponseBuilder().clone(originResponse);
                System.out.println(res.getStatusCode());

                //return originResponse
                String encodeBody = originResponse.getBody().asString();
                System.out.println("encodeBody");
                System.out.println(encodeBody);


                //解密过程
                byte[] decodeBody = Base64.getDecoder().
                        decode(encodeBody.replace("\n","").trim());
                System.out.println("decode");
                System.out.println(new String(decodeBody));

              //Response无法直接修改body，所以间接通过ResponseBuilder构建
                responseBuilder.setBody(decodeBody);

                //ResponseBuilder在最后通过builder方法直接创建一个用于返回的不可修改的response
                Response responseNew  = responseBuilder.build();
                return  responseNew;

            }else{
                return ctx.next(req,res);
            }
        });
    }
}
