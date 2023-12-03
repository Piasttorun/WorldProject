package com.sparta.jarjarbinks.worldproject;

import com.sparta.jarjarbinks.worldproject.controller.WorldController;
import com.sparta.jarjarbinks.worldproject.model.entities.CityDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WorldController.class)
public class MockUpdateTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private WorldController worldController;

    @Test
    @DisplayName("Test Update City")
    void testUpdateCity() throws Exception {

        CityDTO existingMockCity = new CityDTO();
        existingMockCity.setName("existingMockCity");
        CityDTO newMockCity = new CityDTO();
        newMockCity.setName("newMockCity");

        existingMockCity.setId(1);
        newMockCity.setId(1);

        Mockito.when(worldController.getCityById()).thenReturn(new ArrayList<>(List.of(newMockCity)));
        mockMvc
                .perform(MockMvcRequestBuilders.patch("https://localhost:8080/city/1"))
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"name\":\"newMockCity\"}")
                .andExpect(status().is(200));
    }

}
