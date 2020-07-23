package com.test_web.pageobject;

import org.junit.jupiter.api.*;

import static org.junit.Assert.assertEquals;

/**
 * 1.控制test的执行顺序 --使用order注释
 * @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
 * class TestExecutionOrderWithOrderAnnotation {
 *
 *     @Order(1)
 *     @Test
 *     void aTest() {}
 *
 *     @Order(2)
 *     @Test
 *     void bTest() {}
 *
 *     @Order(3)
 *     @Test
 *     void cTest() {}

 2.使用字母顺序-写在类上面，不写这个默认也是按照字母的顺序来的。
 @TestMethodOrder(MethodOrderer.Alphanumeric.class)
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestContact {

    static MainPage main;
    static ContactPage contact;

   @BeforeAll
    static void  beforeAll(){
       main = new MainPage();
       contact = main.toContact();
   }

    @Order(1)
    @Test
    void testAddMember(){

       contact.addMember("丽丽","5","17501670005");
//       String username = contact.addMember("丽丽","5","17501670005").search("丽丽").getUserName();
//       这个地方定位不一样
//        assertEquals(username,"丽丽");
        //todo:assert

    }

    @Order(2)
    @Test()
    void search() {
       contact.search("丽丽").delete();
    }

    @Order(3)
    @Test
    public void testImportFromFile(){
       contact.importFromFile(this.getClass().getResource(""));
    }

    @AfterAll
    static void tearDown(){

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        contact.quit();
    }



}
