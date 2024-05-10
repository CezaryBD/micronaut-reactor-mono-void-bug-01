package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;

@MicronautTest
class DemoTest {

    private static final int wireMockPort = 6969;
    private static final String app = "http://localhost:7000";
    WireMockServer wireMockServer;

    @Inject
    @Client(app)
    HttpClient httpClient;

    @Inject
    EmbeddedApplication<?> application;

    @BeforeEach
    void init() {
        wireMockServer = new WireMockServer(wireMockPort);
        wireMockServer.start();
    }

    @AfterEach
    void clear() {
        wireMockServer.stop();
    }

    @Test
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());
    }

    //this works
    @Test
    public void testNotSimpleVoidReturns204() {
        wireMockServer.stubFor(WireMock.get(WireMock.urlPathEqualTo("/not-simple-void"))
                .willReturn(WireMock.aResponse().withStatus(204)));

        HttpResponse<?> response = httpClient.toBlocking().exchange("/not-simple-void", String.class);
        Assertions.assertEquals(204, response.getStatus().getCode());
    }

    // here 200
    @Test
    public void testSimpleVoidReturns204() {
        wireMockServer.stubFor(WireMock.get(WireMock.urlPathEqualTo("/simple-void"))
                .willReturn(WireMock.aResponse().withStatus(204)));

        HttpResponse<?> response = httpClient.toBlocking().exchange("/simple-void", String.class);
        Assertions.assertEquals(204, response.getStatus().getCode());
    }

}
