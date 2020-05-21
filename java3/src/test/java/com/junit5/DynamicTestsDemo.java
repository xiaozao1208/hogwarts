package com.junit5;

import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.ThrowingConsumer;

import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

/**
 * Dynamic 动态测试用例
 */
public class DynamicTestsDemo {

        public  boolean isPailndrome(String str){ return true;}

        //this will result in a JUnitException
        @TestFactory
        List<String> dynamicTestsWithInvalidReturnType() {
            return Arrays.asList("Hello");
        }



        @TestFactory
        Collection<DynamicTest> dynamicTestsForemCollection(){
            return Arrays.asList(
                    dynamicTest("1st dynamic  test",
                            () ->{
                                assertTrue(isPailndrome("madam"));
                            }),
                    dynamicTest("2nd dynamic test",
                            () -> assertEquals(4,4))
            );
        }

        @TestFactory
        Iterable<DynamicTest> dynamicTestsFormIterable(){
            return Arrays.asList(

                    dynamicTest("3rd dynamic test",
                            () -> assertTrue(isPailndrome("madam"))),
                    dynamicTest("4 dynamic test",
                            () ->assertEquals(3,3))
            );
        }

        @TestFactory
        Iterator<DynamicTest> dynamicTestsIterator(){
            return Arrays.asList(
                    dynamicTest("5 dynamic test",
                            () -> assertTrue(isPailndrome("madam"))),
                    dynamicTest("6 dynamic test",
                            () ->assertEquals(2,2))
            ).iterator();
        }

        @TestFactory
        DynamicTest[] dynamicTestsFormArray(){
            return new DynamicTest[]{
                    dynamicTest("7 dynamic test",
                            () -> assertTrue(isPailndrome("madam"))),
                    dynamicTest("8 dynamic test",
                            () ->assertEquals(8,8))
            };
        }

        @TestFactory
        Stream<DynamicTest> dynamicTestsFormStream(){
            return Stream.of("racecar","radar","mom","dad")
                    .map(text -> dynamicTest(text, () -> assertTrue( isPailndrome(text))));
        }


        @TestFactory
        Stream<DynamicTest> dynamicTestsFromIntStream() {
            return IntStream.iterate(0, n -> + 2).limit(10)
                    .mapToObj(n -> dynamicTest("test"+n,() -> assertTrue(n%2 == 0)));
        }

        @TestFactory
        Stream<DynamicTest>  generateRandomNumberOfTests(){

            Iterator<Integer> inputGenerator = new Iterator<Integer>(){
                Random random = new Random();
                int current;

                @Override
                public  boolean hasNext(){
                    current = random.nextInt(100);
                    return  current % 7 != 0;
                }

                @Override
                public Integer next(){
                    return current;
                }
            };

            Function<Integer, String> disPlayNameGenerator = (input) -> "input"+input;

            ThrowingConsumer<Integer> testExecutor = (input) -> assertTrue(input % 7 != 0);

            return DynamicTest.stream(inputGenerator,disPlayNameGenerator,testExecutor);
        }


        @TestFactory
        Stream<DynamicNode>  dynamicTestsWithContainers(){
            return Stream.of("A","B","c")
                    .map(input -> dynamicContainer("Container" + input,Stream.of(
                            dynamicTest("not null", () -> assertNotNull(input)),
                            dynamicContainer("properities",Stream.of(
                                    dynamicTest("length>0",() ->assertTrue(input.length() > 0)),
                                    dynamicTest("not empty",() ->assertFalse(input.isEmpty()))
                            ))
                    )));
        }


        @TestFactory
        DynamicNode dynamicNodeSingleTest(){
            return dynamicTest("'pop' is a palindrome",() ->assertTrue(isPailndrome("str")));
        }

        @TestFactory
        DynamicNode dynamicNodeSingleContainer() {
            return dynamicContainer("palindromes",
                    Stream.of("racecar", "radar", "mom", "dad")
                            .map(text -> dynamicTest(text, () -> assertTrue(isPailndrome(text)))
                            ));
        }


}

