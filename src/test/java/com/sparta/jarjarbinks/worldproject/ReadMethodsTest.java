package com.sparta.jarjarbinks.worldproject;

import com.sparta.jarjarbinks.worldproject.model.entities.CityDTO;
import com.sparta.jarjarbinks.worldproject.model.repositories.CityRepository;
import com.sparta.jarjarbinks.worldproject.model.repositories.CountryRepository;
import com.sparta.jarjarbinks.worldproject.model.repositories.CountrylanguageRepository;
import com.sparta.jarjarbinks.worldproject.model.services.WorldService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ReadMethodsTest {

    @Autowired
    private WorldService worldService;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private CountrylanguageRepository countrylanguageRepository;

    @BeforeAll
    static void setup() {
    }

    @Test
    public void testGetCityById() {
        Optional<CityDTO> city = worldService.getCityById(1);
        assertTrue(city.isPresent());
    }
}

