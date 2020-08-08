package com.test_framework;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import java.net.URL;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * 生成对应的单元测试的快捷键： ctrl + shift + t
 * 或者是：Navigate ---test
 *
 * 价值（参数化）
 * -简化自动化测试技术
 * -规范领域测试模型
 * -数据驱动与api可以结合；
 * -自动生成用例（接口多，UI也有）--分析的结构化数据，生成代码格式的用例非常复杂，直接保存为数据格式更好。
 * -与云平台对接：数据保存到了数据库的表结构里，数据的传输转化也更适合使用数据。
 *
 *
 * TDD风格的测试体系：基于api的测试框架可以满足绝大部分的需要 junit+testng+po+param  适合测试开发
 *  TDD优势：IDE的智能操作，比如重构，自动提示等很强
 * DDT风格的测试体系: 非测试人员（业务测试，产品，研发啊，甲方），平台（与其他框架对接，自动生成，框切换，平台调度）
 *
 * 模型model
 * 把领域知识用class进行表达（OOP理念）
 *  -PO的模型需要独立的：restful
 *  -自动化领域模型：web,app,service
 *  -测试用例的模型会引用其他的模型定义：xUnit
 *
 * 流量回放（自动化测试） 什么意思？？
 * Diff
 * 接口Diff测试，简单来说就是比对相同接口在不同版本/不同环境下面的返回内容是否符合预期
 * 代码diff与测试相结合，可以对测试起到较明显的促进作用  什么意思？？
 *
 */

class BasePageTest {

    private static BasePage basePage;

    @BeforeAll
    static  void  beforeAll(){

        basePage = new BasePage();
    }

    @AfterAll
    static void  afterAll(){

    }

    @BeforeEach
    void setUp(){

    }


    @AfterEach
    void tearDown(){

    }


    @Test
    void run(UIAuto uiauto) {
        uiauto.steps.stream().forEach(m->{
            if(m.keySet().contains("click")){
//                click(m.get("click").toString());
            }

        });
    }

    @Test
    void load() throws JsonProcessingException {

         UIAuto uiauto = basePage.load("/test_framework/uiauto.yaml");
        // 打印出结果
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(uiauto));

    }
}