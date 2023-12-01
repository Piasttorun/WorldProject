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
        CityDTO testCity = new CityDTO();
        testCity.setId(1);
        testCity.setName("Test City");
        testCity.setCountryCode("TST");
        testCity.setDistrict("Test District");
        testCity.setPopulation(100000);
        cityRepository.save(testCity);

        boolean cityExists = worldService.getCityById(testCity.getId()).isPresent();
        assertTrue(cityExists, "City with ID " + testCity.getId() + " should exist.");
    }

    @Test
    public void testCountryExistsById() throws NotFoundException {
        CountryDTO testCountry = new CountryDTO();
        testCountry.setCode("TST");
        testCountry.setName("Test Country");
        testCountry.setContinent("Test Continent");
        testCountry.setRegion("Test Region");
        testCountry.setSurfaceArea(BigDecimal.valueOf(123.456));
        testCountry.setPopulation(1000000);
        countryRepository.save(testCountry);

        boolean countryExists = worldService.getCountryById(testCountry.getCode()).isPresent();
        assertTrue(countryExists, "Country with Code " + testCountry.getCode() + " should exist.");
    }

    @Test
    public void testGetCountrylanguageById() {
        CountrylanguageDTO testLanguage = new CountrylanguageDTO();
        CountrylanguageIdDTO languageIdDTO = new CountrylanguageIdDTO();
        languageIdDTO.setCountryCode("TST");
        languageIdDTO.setLanguage("Test Language");
        testLanguage.setId(languageIdDTO);
        testLanguage.setIsOfficial(true);
        testLanguage.setPercentage(BigDecimal.valueOf(50));
        countrylanguageRepository.save(testLanguage);

        CountrylanguageDTO retrievedLanguage = worldService.getCountryLanguageById(languageIdDTO);
        assertEquals(testLanguage, retrievedLanguage, "Retrieved language should match the saved language.");
    }

    @Test
    public void testGetCity() {
        CityDTO testCity = new CityDTO();
        testCity.setId(1);
        testCity.setName("Test City");
        testCity.setCountryCode("TST");
        testCity.setDistrict("Test District");
        testCity.setPopulation(100000);
        cityRepository.save(testCity);

        List<CityDTO> cities = worldService.getCity();
        assertTrue(cities.contains(testCity), "Cities should contain the test city.");
    }

    @Test
    public void testGetCountry() {
        CountryDTO testCountry = new CountryDTO();
        testCountry.setCode("TST");
        testCountry.setName("Test Country");
        testCountry.setContinent("Test Continent");
        testCountry.setRegion("Test Region");
        testCountry.setSurfaceArea(BigDecimal.valueOf(123.456));
        testCountry.setPopulation(1000000);
        countryRepository.save(testCountry);

        List<CountryDTO> countries = worldService.getCountry();
        assertTrue(countries.contains(testCountry), "Countries should contain the test country.");
    }

    @Test
    public void testGetCountrylanguage() {
        CountrylanguageDTO testLanguage = new CountrylanguageDTO();
        CountrylanguageIdDTO languageIdDTO = new CountrylanguageIdDTO();
        languageIdDTO.setCountryCode("TST");
        languageIdDTO.setLanguage("Test Language");
        testLanguage.setId(languageIdDTO);
        testLanguage.setIsOfficial(true);
        testLanguage.setPercentage(BigDecimal.valueOf(50));
        countrylanguageRepository.save(testLanguage);

        List<CountrylanguageDTO> languages = worldService.getCountrylanguage();
        assertTrue(languages.contains(testLanguage), "Languages should contain the test language.");
    }




}

