package com.suite;

import org.junit.runner.RunWith;
import

@RunWith(JUnitPlatform.class)
@SelectPackages({
        "com.testcaseinfo"
})
@IncludePackages({
        "com.testcaseinfo.testcase",
        "com.testcaseinfo.testcase2"
})
public class Junit5SuiteDemo1Test {
}
