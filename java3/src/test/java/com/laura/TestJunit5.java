package com.laura;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestJunit5 {

    @Test
    @Tag("print")
     void  demo1(){
        System.out.println("执行 demo1 test");
    }

    @Test
    @Tag("print")
    void  demo2(){
        System.out.println("执行 demo2 test");
    }


    @Test
    @Tag("assert")
    void  demo3(){assertEquals(1,1); }

    @Test
    @Tag("assert")
    void  demo4(){ System.out.println("执行 demo4 test"); }

    @Tag("print")
    @Test
    void  demo5(){
        assertEquals(1,1);
    }
}
