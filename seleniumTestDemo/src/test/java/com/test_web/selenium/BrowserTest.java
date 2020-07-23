package com.test_web.selenium;

import org.junit.jupiter.api.Test;

public class BrowserTest extends BaseTest {

    /**
     * browser="chrome" mvn -Dtest=BrowserTest test
     * 执行上面命令失败，原因是加了下面的依赖，去掉后就正常，为什么呢？
     * --后面加上后执行也可以了？？？
     *  <dependency>
     *  <groupId>org.seleniumhq.selenium</groupId>
     *  <artifactId>selenium-java</artifactId>
     *  <version>3.14.0</version>
     *  </dependency>
     *
     *
     *  <!--执行测试类插件-->
     *             <plugin>
     *                 <groupId>org.apache.maven.plugins</groupId>
     *                 <artifactId>maven-surefire-plugin</artifactId>
     *                 <version>2.22.2</version>   --版本由3.0降到2.22.2
     *                 <configuration>
     *                     <testFailureIgnore>true</testFailureIgnore>
     *                 </configuration>
     *             </plugin>
     *
     *
     */
    @Test
    void browserTest(){

        driver.get("https://home.testing-studio.com");
    }
}

