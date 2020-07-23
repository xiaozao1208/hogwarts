package com.wechat.utils;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * faker伪造工具
 *获取系统时间戳
 */
public class FakerUtils {

    private static final Logger logger = LoggerFactory.getLogger(FakerUtils.class);

    public static String getTimeStamp(){
        return String.valueOf(System.currentTimeMillis());
    }

    @Test
    void  test(){
        logger.info(getTimeStamp());
    }
}
