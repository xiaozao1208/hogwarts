package com.wechat.testcases;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DemoTest {

    private static  final Logger logger = LoggerFactory.getLogger(DemoTest.class);

    @Test
    void test(){
        logger.info("hello world");
    }
}
