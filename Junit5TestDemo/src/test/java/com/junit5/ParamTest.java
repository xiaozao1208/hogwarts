package com.junit5;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *   @ParameterizedTest
 *
 *     @ValueSource 简单情况
 *     @EnumSource
 *     @MethodSource 复杂的使用比较多
 *     @CsvSource
 *     @CsvFileSource 通过文件读取
 *     @ArgumentsSource
 */

public class ParamTest {

    @ParameterizedTest
    @ValueSource(ints ={1,2,3})
    void  testWithValueSource( int argument){
        assertTrue(argument > 0 && argument <4);
    }



    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings ={" ","    ","\t","\n"})
    public void  nullEmptyAndBlankString(String text){
       assertTrue(text == null || text.trim().isEmpty());
    }



}
