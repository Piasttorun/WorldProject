package com.sparta.jarjarbinks.worldproject;

import com.sparta.jarjarbinks.worldproject.exceptions.NotFoundException;
import com.sparta.jarjarbinks.worldproject.model.entities.CityDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountryDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountrylanguageDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountrylanguageIdDTO;
import com.sparta.jarjarbinks.worldproject.model.repositories.CityRepository;
import com.sparta.jarjarbinks.worldproject.model.repositories.CountryRepository;
import com.sparta.jarjarbinks.worldproject.model.repositories.CountrylanguageRepository;
import com.sparta.jarjarbinks.worldproject.model.services.WorldService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    public void testCityExistsById() throws NotFoundException {
        Optional<CityDTO> fetchedCity = worldService.getCityById(5);

        assertTrue(fetchedCity.isPresent());
        assertEquals("Amsterdam", fetchedCity.get().getName());

    }

    @Test
    public void testCountryExistsById() throws NotFoundException {
       Optional<CountryDTO> fetchedCountry = worldService.getCountryById("ABW");

       assertTrue(fetchedCountry.isPresent());
       assertEquals("Aruba", fetchedCountry.get().getName());
    }

    @Test
    public void testCountryLanaguageExistsById() {
        boolean flag = false;
        Optional<CountryDTO> fetchedCountry = worldService.getCountryById("ABW");
        List<CountrylanguageDTO> languageList = countrylanguageRepository.findCountrylanguageDTOByCountryCode(fetchedCountry.get());
        for (CountrylanguageDTO language: languageList) {
            if (language.getId().getLanguage().equals("English")) {
                flag = true;
            }
        }
        assertTrue(flag);
    }


}

