package com.test_services;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.transform.Result;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;


public class StubTest {

    /**
     * 启动一个端口为8089服务wireMockConfig
     */
    @BeforeAll
    static void beforeAll(){
        WireMockServer wireMockServer = new WireMockServer(wireMockConfig().port(8087));
        //No-args constructor will start on port 8080, no HTTPS
        wireMockServer.start();
        configureFor("localhost",8087);
    }

    @Test
    void stubTest() throws InterruptedException {
            stubFor(get(urlEqualTo("/user/d"))
                    .withHeader("Accept", equalTo("text/xml"))
                    .willReturn(aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", "text/xml")
                            .withBody("<response>d info</response>")));

                  Thread.sleep(10000);

            reset();

            stubFor(get(urlEqualTo("/user/d"))
                    .withHeader("Accept", equalTo("text/xml"))
                    .willReturn(aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", "text/xml")
                            .withBody("<response>exception</response>")));

        //todo：use
        }



    @Test
    void mockOnStub() throws InterruptedException {
        stubFor(get(urlEqualTo("/user/d"))
                .withHeader("Accept", equalTo("text/xml"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml")
                        .withBody("<response>A</response>")));

        //todo：use
        Thread.sleep(100000);

        stubFor(get(urlEqualTo("/user/d"))
                .withHeader("Accept", equalTo("text/xml"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml")
                        .withBody("<response>B</response>")));
    }

    /**
     * 透明代理 或者 转发服务器
     */
    @Test
    void mockOnProxy(){
        stubFor(
                get(urlMatching(".*"))
                .atPriority(10)
                .willReturn(aResponse().proxiedFrom("https://ceshiren.com"))
        );


    }

}
