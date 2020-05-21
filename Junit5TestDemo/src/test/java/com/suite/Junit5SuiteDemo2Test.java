package com.suite;


import com.testcaseinfo.testcase.Junit5Demo2Test;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({
        Junit5Demo2Test.class
})
@IncludeTags({
        "testdemo"
})
public class Junit5SuiteDemo2Test {
}
