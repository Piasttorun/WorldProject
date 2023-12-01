package com.sparta.jarjarbinks.worldproject;

import com.sparta.jarjarbinks.worldproject.controller.WorldController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.WebClientRestTemplateAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//        import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
//        import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
//        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//        import com.sparta.mnm.libraryproject.controller.LibraryController;
//        import com.sparta.mnm.libraryproject.model.enities.AuthorDTO;
//        import org.junit.jupiter.api.DisplayName;
//        import org.junit.jupiter.api.Test;
//        import org.mockito.Mockito;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//        import org.springframework.boot.test.mock.mockito.MockBean;
//        import org.springframework.test.web.servlet.MockMvc;
//        import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//        import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//
//        import java.util.ArrayList;
//        import java.util.List;
//
//@WebMvcTest(LibraryController.class)
//public class MockingTests {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private LibraryController authorRepository;
//
//    @Test
//    @DisplayName("Test Rest Response")
//    void testRestResponse() throws Exception {
//        AuthorDTO mockAuthor = new AuthorDTO();
//        mockAuthor.setFullName("Mock Man!!");
//        Mockito.when(authorRepository.findAll()).thenReturn(new ArrayList<>(List.of(mockAuthor)));
//        mockMvc
//                .perform(MockMvcRequestBuilders.get("https://localhost:5000/authors"))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(status().is(200))
//                .andExpect(content().contentType("application/json"));
//        //.andExpect(content().toString().contains("Mock Man!!"));
//    }
//}
