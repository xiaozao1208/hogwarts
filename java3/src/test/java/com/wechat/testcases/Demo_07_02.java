package com.wechat.testcases;

import com.github.jknack.handlebars.cache.ConcurrentMapTemplateCache;
import com.sun.org.glassfish.gmbal.Description;
import com.wechat.apiobject.DepartmentApiObject;
import com.wechat.apiobject.TokenHelper;
import com.wechat.utils.FakerUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

/**
 * 7.多线程测试---并发测试
 * 非分布式服务器---多并发测试
 * 分布式服务器----多并发测试
 *
 * 并发复现---插入和修改同时进行，看是否会出现
 *
 */


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Demo_07_02 {

        private static final Logger logger = LoggerFactory.getLogger(Demo_07_02.class);
        static String  assessToken;

        @BeforeAll
        static void  getAssessToken(){

            assessToken = TokenHelper.getAssessToken();
            logger.info(assessToken);
        }


    /**
     * 1.第一种情况:20个线程，5个并发，同样的参数，入参，同时请求，应该只有一个成功，其他的失败；--做了线程安全；
     *结果：只有一个线程成功，其他是失败的。
     */
        @DisplayName("创建部门")
        @RepeatedTest(20)
        @Execution(CONCURRENT)
        void createDepartment(){

            String createName="createName";
            String createNameEn = "createNameEn";
            Response createResponse = DepartmentApiObject.createDepartment(createName,createNameEn,assessToken);

            assertEquals("0",createResponse.path("errcode").toString());
        }



        @Test
        @Description("更新部门信息")
        @RepeatedTest(10)
        void updateDepartment(){

            String createName="createName"+ FakerUtils.getTimeStamp();
            String createNameEn = "createNameEn"+FakerUtils.getTimeStamp();
            Response createResponse = DepartmentApiObject.createDepartment(createName,createNameEn,assessToken);
            String departmentId = createResponse.path("id") !=null ?createResponse.path("id").toString() : null;

            String updateName="updateName"+ FakerUtils.getTimeStamp();
            String updateNameEn ="updateNameEn"+FakerUtils.getTimeStamp();
            Response updateResponse = DepartmentApiObject.updateDepartment(updateName,updateNameEn,assessToken,departmentId);
            assertEquals("0",createResponse.path("errcode").toString());
        }



}


