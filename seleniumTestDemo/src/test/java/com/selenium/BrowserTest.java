package com.selenium;

import org.junit.jupiter.api.Test;

public class BrowserTest extends BaseTest {

    @Test
    public void browserTest(){
        driver.get("https://home.testing-studio.com");
    }
}
