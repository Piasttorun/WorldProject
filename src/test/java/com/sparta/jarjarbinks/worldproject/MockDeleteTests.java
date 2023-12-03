package com.sparta.jarjarbinks.worldproject;

import com.sparta.jarjarbinks.worldproject.controller.WorldController;
import com.sparta.jarjarbinks.worldproject.model.entities.CityDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountryDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountrylanguageDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountrylanguageIdDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WorldController.class)
public class MockDeleteTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private WorldController worldController;

    @Test
    @DisplayName("Test Delete City")
    void testDeleteCity() throws Exception {
        CityDTO mockCity = new CityDTO();
        mockCity.setName("Heaven");
        mockCity.setId(1);

        Mockito.when(worldController.getCityById()).thenReturn(new ArrayList<>(List.of(mockCity)));
        mockMvc
                .perform(MockMvcRequestBuilders.delete("https://localhost:8080/city/1"))
                .andExpect(status().is(200));
    }

    @Test
    @DisplayName("Test Delete Country")
    void testDeleteCountry() throws Exception {
        CountryDTO mockCountry = new CountryDTO();
        mockCountry.setName("Heaven");
        mockCountry.setCode("1");


        Mockito.when(worldController.getCountry()).thenReturn(new ArrayList<>(List.of(mockCountry)));
        mockMvc
                .perform(MockMvcRequestBuilders.delete("https://localhost:8080/country/1"))
                .andExpect(status().is(200));
    }

    @Test
    @DisplayName("Test Delete Language")
    void testDeleteLanguage() throws Exception {
        CountrylanguageDTO mockLanguage =  new CountrylanguageDTO();
        CountrylanguageIdDTO Help = new CountrylanguageIdDTO();
        Help.setCountryCode("WWW");
        Help.setLanguage("English");
        mockLanguage.setId(Help);
        String jsonPayload = "{\"newCountryId\": \"Help\"}";

        Mockito.when(worldController.getCountryLanguage()).thenReturn(new ArrayList<>(List.of(mockLanguage)));
        mockMvc
                .perform(MockMvcRequestBuilders.delete("https://localhost:8080/country_language/English/WWW"))
                .andExpect(status().is(200));

    }
}
