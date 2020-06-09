package com.testcase;

import com.pageobject.MainPage;
import org.junit.jupiter.api.Test;

public class TestContact {

    @Test
    void testAddMember(){
        MainPage main = new MainPage();
        main.toContact().addMember("3","3","17301670001");

        //todo:assert

    }
}
