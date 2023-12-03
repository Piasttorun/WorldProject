package com.sparta.jarjarbinks.worldproject;

import com.sparta.jarjarbinks.worldproject.controller.WorldController;
import com.sparta.jarjarbinks.worldproject.exceptions.InvalidArgumentFormatException;
import com.sparta.jarjarbinks.worldproject.exceptions.NotFoundException;
import com.sparta.jarjarbinks.worldproject.model.entities.CityDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountryDTO;
import com.sparta.jarjarbinks.worldproject.model.repositories.CityRepository;
import com.sparta.jarjarbinks.worldproject.model.repositories.CountryRepository;
import com.sparta.jarjarbinks.worldproject.model.repositories.CountrylanguageRepository;
import com.sparta.jarjarbinks.worldproject.model.services.WorldService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UpdateMethodsTests {

    @MockBean
    private CityRepository cityRepositoryTest;
    @MockBean
    private CountryRepository countryRepositoryTest;
    @MockBean
    private CountrylanguageRepository countryLanguageRepositoryTest;
    @Autowired
    private final WorldService worldService = new WorldService(cityRepositoryTest, countryRepositoryTest, countryLanguageRepositoryTest);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPutCityWithValidId() throws NotFoundException, InvalidArgumentFormatException {

        CityDTO existingCityDTO = new CityDTO();
        existingCityDTO.setId(1);
        existingCityDTO.setDistrict("District To Update");
        existingCityDTO.setName("City To Update");
        existingCityDTO.setPopulation(10000);
        existingCityDTO.setCountryCode(new CountryDTO());

        cityRepositoryTest.save(existingCityDTO);

        CityDTO newCityDTO = new CityDTO();
        newCityDTO.setId(1);
        newCityDTO.setDistrict("Updated District");
        newCityDTO.setName("Updated City");
        newCityDTO.setPopulation(20000);
        existingCityDTO.setCountryCode(new CountryDTO());

        worldService.putCity(newCityDTO, 1);
        Optional<CityDTO> updatedCity = worldService.getCityById(1);
        assertEquals(updatedCity, Optional.of(newCityDTO));

    }


    @Test
    @DisplayName("Check that country is updated")
    void putCountryTest() {
    }

    @Test
    @DisplayName("Check that country language is updated")
    void putCountryLanguageTest() {
    }

}
