package com.test_app.snowball.testcase;

import com.test_app.snowball.page.SearchPage;
import com.test_app.snowball.page.MainPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchPageTest {

    static MainPage mainPage;
    static SearchPage searchPage;

    @BeforeAll
    static void beforeAll(){
        searchPage = new MainPage().toSearch();
    }


    @ParameterizedTest
    @CsvSource({
            "alibaba, 阿里巴巴",
            "jd,京东"
    })
    @Test
    void testSearch(String keyword,String name){
        assertEquals(searchPage.search(keyword).getSearchList().get(0),name);
        

    }



    @Test
    void testGetPrice(){
       assertTrue(searchPage.search("alibaba").getPrice() >200);
    }


    @AfterAll
    static void  afterAll(){
        mainPage.quit();
    }




}
