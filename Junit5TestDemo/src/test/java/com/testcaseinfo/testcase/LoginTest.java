package com.testcaseinfo.testcase;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.HashMap;


/**
 * 执行顺序：
 * 1.@Test
 *     void loginTest(){}
 * 2.@Nested
 *     class BuyTest{}
*  3.@Nested
 *     class PayTest{}
 * 除了@Test主要测试方法外，@nested的类倒序执行。
 */
public class LoginTest {

    private static HashMap<String,Object> dataMap = new HashMap<String,Object>();

    @Test
    void loginTest(){
        dataMap.put("login","登录成功");
        System.out.println(dataMap.get("login"));
    }


    //内嵌测试
    @Nested
    class PayTest{

        @Test
        void payTest(){
            if(dataMap.get("buy").equals("购买了XX商品")){
                System.out.println("正在支付，请等待...");
                dataMap.put("buy","购买了XX商品");
            }else{
                System.out.println("还未添加商品");
            }
        }

    }


    @Nested
    class BuyTest{

        @Test
        void buyTest(){
            if(dataMap.get("login").equals("登录成功")){
                System.out.println("登录成功，可以购买商品");
                dataMap.put("buy","购买了XX商品");
            }else{
                System.out.println("还未登录，请先登录！！");
            }
        }

    }


}
