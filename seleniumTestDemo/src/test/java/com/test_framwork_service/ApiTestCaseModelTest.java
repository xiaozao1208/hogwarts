package com.test_framwork_service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiTestCaseModelTest {



    @BeforeAll
    static void beforeAll() {
        api = ApiObjectModel.load("");
    }


    @Test
    void load() {
    }
}