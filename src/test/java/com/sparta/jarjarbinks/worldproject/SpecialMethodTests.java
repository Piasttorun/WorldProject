package com.sparta.jarjarbinks.worldproject;

//import com.sparta.jarjarbinks.worldproject.controller.WorldController;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.client.WebClientRestTemplateAutoConfiguration;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.reactive.server.WebTestClient;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//public class SpecialMethodTests {
//
//    private WebTestClient webTestClient;
//
//    @Autowired
//    private WorldController worldController;
//
//    @BeforeEach
//    void setup() {
//        webTestClient = WebTestClient.bindToController(worldController).build();
//    }
//
//    @Test
//    @DisplayName("hello")
//    void checkThatTheStatusCodeIs200() {
//        webTestClient
//                .get()
//                .uri("http://localhost:8080/authors")
//                .exchange()
//                .expectStatus()
//                .isEqualTo(407);
//    }
//}

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.sparta.jarjarbinks.worldproject.controller.WorldController;
import com.sparta.jarjarbinks.worldproject.model.entities.CountryDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import java.util.ArrayList;
import java.util.List;

@WebMvcTest(WorldController.class)
public class SpecialMethodTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private WorldController worldController;

    @Test
    @DisplayName("Test Rest Response")
    void testCountriesNoHeadOfState() throws Exception {
        CountryDTO mockCountry = new CountryDTO();
        Mockito.when(worldController.getCountry()).thenReturn(new ArrayList<>(List.of(mockCountry)));
        mockMvc
                .perform(MockMvcRequestBuilders.get("https://localhost:8080/country"))
                .andExpect(status().is(200))
                .andExpect(content().contentType("application/json"));
        //.andExpect(content().toString().contains("Mock Man!!"));
    }
}
