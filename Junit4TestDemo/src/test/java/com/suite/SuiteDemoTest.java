package com.suite;


import com.testcase.BuyTest;
import com.testcase.LoginTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTest.class,
        BuyTest.class}
        /**
         * 此处类的配置顺序会影响执行顺序
        */
)
public class SuiteDemoTest {

}
