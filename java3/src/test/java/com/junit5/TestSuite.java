package com.junit5;


import com.junit5.ParamTest;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SuiteDisplayName("junit Platform suite demo")
@SelectClasses({
        ParamTest.class
})
public class TestSuite {

}


/*
@SelectPackages("junit5")  针对包
@SelectClasses({          针对类
        CalcTest.class,
        ParamTest.class
})

@RunWith(JUnitPlatform.class)
@SuiteDisplayName("junit Platform suite demo")
@SelectPackages("junit5")
public class TestSuite {

}
*/