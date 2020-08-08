package com.laura;


import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({
        TestJunit5.class
})
@IncludeTags({
        "print"
})
public class JunitPlatformTest {
}


