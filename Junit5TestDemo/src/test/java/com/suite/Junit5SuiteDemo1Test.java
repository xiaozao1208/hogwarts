package com.suite;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludePackages;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages({
        "com.testcaseinfo"
})
@IncludePackages({
        "com.testcaseinfo.testcase1",
        "com.testcaseinfo.testcase2"
})
public class Junit5SuiteDemo1Test {
}
