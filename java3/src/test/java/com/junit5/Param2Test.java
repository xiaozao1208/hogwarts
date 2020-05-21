package com.junit5;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import com.fasterxml.jackson.annotation.JsonRawValue;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.lang.reflect.Array;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Param2Test {

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 8})
    void testNumberShouldBeEven(int num) {
        assertEquals(0, num % 2);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Radar", "Rotor", "Tenet", "Mad", "Racecar"})
    void testStringShouldBePalindrome(String word) {
        assertEquals(isPalindrome(word), true);
        /**
         * isPalindrome(word) 判断参数是否是回文数
         */
    }

    @ParameterizedTest
    @ValueSource(doubles = {2.D, 4.D, 8.D})
    void testDoubleNumberBeEven(double num) {
        assertEquals(0, num % 2);
    }

    boolean isPalindrome(String word) {
        return word.toLowerCase().equals(new StringBuffer(word.toLowerCase()).reverse().toString());
    }


   /* 这里执行的时候怎么是7 ，不是5？？*/
    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings={" ","   ","\t","\n",""})
    void nullEmptyAndBlankStrings(String text){
        assertTrue(text == null || text.trim().isEmpty());
    }


    /*枚举的类：ChronoUnit.class, TemporalUnit是前者的父类 */
    @ParameterizedTest
    @EnumSource(ChronoUnit.class)
    void  testWithEnumSource(TemporalUnit unit){
        assertNotNull(unit);
    }


    /* 方法类参数化
    * ？？为什么此处为空，断言是成功的？
    * */
    @ParameterizedTest
    @MethodSource
    void  testWithDefaultLocalMethodSource(String argument){
        assertNotNull(argument);
    }

    static Stream<String> testWithDefaultLocalMethodSource(){
        return Stream.of("apple","banana","");
    }


    /* 与上一个方法比较一下 方法名的传入形式*/
    @ParameterizedTest
    @MethodSource("stringProvider")
    void  testWithExplicitLocalMethodSource(String argument){
        assertNotNull(argument);
    }

    static Stream<String> stringProvider(){
        return Stream.of("apple","banana","pig");
    }


    /**
     *这种组合形式的使用场景？？
     */
    @ParameterizedTest
    @MethodSource("stringIntAndListProvider")
    void  testWithMultiArgMethodSource(String str, int num, List<String> list){
          assertEquals(5,str.length());
          assertTrue(num >=1 && num <=3);
          assertEquals(2,list.size());
    }


    static  Stream<Arguments> stringIntAndListProvider(){
        return Stream.of(
                arguments("apple",1, Arrays.asList("a","b")),
                arguments("lemon",3,Arrays.asList("x","y"))
        );
    }


    /**
     * CSV提供数据源的方式,
     *    1.数量少，直接填写； @CsvSource
     *    2.数量多,csv表格导入参数 @CsvFileSource
     * 还需要添加依赖：
     * testCompile "org.junit.jupiter:junit-jupiter-params:${junitJupiterVersion}"
      */
    @ParameterizedTest
    @CsvSource({
            "apple,1",
            "banana,2",
            "'lemon,lime',0xF1",
            "orange,0"
    })
    void testWithCsvSource(String fruit, int rank){
        assertNotNull(fruit);
        assertNotEquals(0,rank);
    }


    /**
     *
     * 2.数量多,csv表格导入参数 @CsvFileSource
         * resources = "/two-colum.csv"  csv文件的路径
     *     numLinesToSkip = 0,
     *    2.1 本地直接输入的csv可以的；
     *    2.2 上传的csv文件乱码，处理下？？？？？？
     *
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/two-colum.csv",numLinesToSkip = 1)
    void  testWithCsvFileSource(String country,int reference){
        assertNotNull(country);
        assertNotEquals(0,reference);
    }




    @ParameterizedTest
    @ArgumentsSource(MyArgumentsProvider.class)
    void testWithArgumentsSource(String argument){
        assertNotNull(argument);
    }

    @Nested
    public  static class MyArgumentsProvider implements  ArgumentsProvider{

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context){
            return Stream.of("apple","banana").map(Arguments::of);
        }
    }
}
