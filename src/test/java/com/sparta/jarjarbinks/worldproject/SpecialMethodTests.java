package com.sparta.jarjarbinks.worldproject;

import com.sparta.jarjarbinks.worldproject.controller.WorldController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.WebClientRestTemplateAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
public class SpecialMethodTests {

    private WebTestClient webTestClient;

    @Autowired
    private WorldController worldController;

    @BeforeEach
    void setup() {
        webTestClient = WebTestClient.bindToController(worldController).build();
    }

    @Test
    @DisplayName("hello")
    void checkThatTheStatusCodeIs200() {
        webTestClient
                .get()
                .uri("http://localhost:8080/authors")
                .exchange()
                .expectStatus()
                .isEqualTo(407);
    }
}
