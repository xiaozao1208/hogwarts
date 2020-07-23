package com.Interfacebase;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.core.IsEqual.equalTo;

public class DemoTest {

    @Test
    public void test() {
        given()
                .get("https://www.baidu.com")
                .then()
                .statusCode(200)
                .log().all();
    }
    //java.lang.RuntimeException: bad non-abstract method with no code:s

    @Test
    public void lottoTest() {

        given()
                .get("http://localhost:8082/lotto")
                .then()
                .body("lotto.lottoId",equalTo(5));

        given()
                .get("http://localhost:8082/lotto")
                .then()
                .body("lotto.winners.winnerId",hasItems(23,54));
    }

}
