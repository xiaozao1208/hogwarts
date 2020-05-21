package com.laura;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestAssertion {

    @Test
    void assertion(){
        assertAll("demo assertions",
                () -> {
                    assertEquals(1,1);
                },

                () -> assertEquals(1,3),
                () -> assertEquals(2,2),
                () -> assertEquals(3,4)

        );

        System.out.println("继续执行下一个断言");

        /**
         * 下面的断言不会执行。 因此建议断言尽量先判断，最后放在一起进行断言；
         * 或者
         */
        assertAll("demo assertions2",
                () -> assertEquals(4,1),
                () -> assertEquals(4,4),
                () -> assertEquals(2,2),
                () -> assertEquals(1,3)

        );
    }
}
