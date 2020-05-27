package com.junit5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalcTest {

    @Test
    void div() {
        Calc calc = new  Calc();
       assertEquals(4,calc.div(20,5));
    }

    @Test
    void  add(){
        Calc calc = new  Calc();
        assertEquals(25,calc.add(20,5));
    }
}