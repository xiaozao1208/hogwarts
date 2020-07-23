package com;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private final Calculator  calculator = new Calculator();
    private final Person person  = new Person("Jane","Doe");

    @Test
    void standardAssertions(){
        assertEquals(2,calculator.add(1,1));
        assertEquals(8,calculator.multiply(2,4));

        //当不相等时，会打印出第三个参数，下面的所有的此类型的参数都是这种作用
        assertEquals(4,5,"the optional assertion message is now the last parameter");

    }


    /**
     * assertAll()方法用于将多个测试语句放在一个组中执行
     * 组中若有一个测试语句不通过，则这个组将会一起报错.
     * 方法中第一个参数：组名称
     * 方法中第二个参数：组测试语句
     */
    @Test
    void groupAssertions(){
        assertAll("person test",
            ()-> assertEquals("Jane",person.getFirstName()),
            ()-> assertEquals("Do",person.getLastName())
        );
    }


    @Test
    void  dependentAssertions(){
        assertAll(
                "properties",
                () -> {
                    String firstName = person.getFirstName();
                    assertNotNull(firstName);

                    assertAll(
                            "firat name",
                            () -> assertTrue(firstName.startsWith("J")),
                            () -> assertTrue(firstName.endsWith("e"))
                    );
                },
                () ->{
                    String lastName = person.getLastName();
                    assertNotNull(lastName);

                    assertAll(
                            "last name",
                            () -> assertTrue(lastName.startsWith("D")),
                            () -> assertTrue(lastName.endsWith("e"))
                    );
                }

        );
    }

    @Test
    void exceptionTesting(){
        Exception exception  = assertThrows(
                ArithmeticException.class ,
                () ->calculator.divide(1,0)
        );
        assertEquals("/ by zero",exception.getMessage());
    }











}