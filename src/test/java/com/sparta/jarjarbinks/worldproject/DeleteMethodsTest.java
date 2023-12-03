package com.sparta.jarjarbinks.worldproject;

import com.sparta.jarjarbinks.worldproject.exceptions.AlreadyExistsException;
import com.sparta.jarjarbinks.worldproject.exceptions.InvalidArgumentFormatException;
import com.sparta.jarjarbinks.worldproject.exceptions.NotFoundException;
import com.sparta.jarjarbinks.worldproject.model.entities.CityDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountryDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountrylanguageDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountrylanguageIdDTO;
import com.sparta.jarjarbinks.worldproject.model.repositories.CountryRepository;
import com.sparta.jarjarbinks.worldproject.model.services.WorldService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest
public class DeleteMethodsTest {

    @Autowired
    private WorldService worldService;

    @Autowired
    private CountryRepository countryRepository;

    static CountryDTO testCountry;
    static CityDTO testCity;

    static CountrylanguageDTO testCountrylang;
    static CountrylanguageIdDTO testCountryLangId;

    @BeforeAll
    public static void setUp(@Autowired WorldService worldService) throws AlreadyExistsException {
        // Create country
        testCountry = new CountryDTO();
        testCountry.setCode("XXX");
        testCountry.setName("MyFakeCountry");
        testCountry.setPopulation(1000);
        testCountry.setRegion("Fakistan");
        testCountry.setContinent("Asia");
        testCountry.setSurfaceArea(BigDecimal.valueOf(2000));
        testCountry.setCode2("XX");
        testCountry.setLocalName("Fakistan");
        testCountry.setGovernmentForm("Fraud");

        // Create city
        testCity = new CityDTO();
        testCity.setId(99999);
        testCity.setName("MyFakeCity");
        testCity.setPopulation(500);
        testCity.setCountryCode(testCountry);
        testCity.setDistrict("NewFakeDistict");

        // Create countrylangid
        testCountryLangId = new CountrylanguageIdDTO();
        testCountryLangId.setLanguage("English");
        testCountryLangId.setCountryCode(testCountry.getCode());

        // Create country lang
        testCountrylang = new CountrylanguageDTO();
        testCountrylang.setIsOfficial("T");
        testCountrylang.setPercentage(BigDecimal.valueOf(25.4));
        testCountrylang.setCountryCode(testCountry);
        testCountrylang.setId(testCountryLangId);


        worldService.createCountry(testCountry);
        worldService.createCity(testCity);
        worldService.createCountryLanguage(testCountrylang);

    }

    @AfterAll
    public static void tearDown(
            @Autowired WorldService worldService,
            @Autowired CountryRepository countryRepository)
            throws NotFoundException, InvalidArgumentFormatException {

        worldService.deleteCountryLanguage(testCountryLangId);
        worldService.deleteCity(testCity.getId());
        countryRepository.deleteById("XXX");
    }


    @Test
    @Transactional
    @DisplayName("Testing if Country Language has been deleted")
    public void testDeleteLanguage() throws NotFoundException, InvalidArgumentFormatException {
        worldService.deleteCountryLanguage(testCountryLangId);
        Optional<CountrylanguageDTO> fetchedLanguage = Optional.ofNullable(worldService.getCountryLanguageById(testCountryLangId));
        Assertions.assertTrue(fetchedLanguage.isEmpty());
    }

    @Test
    @Transactional
    @DisplayName("Testing if City has been deleted")
    public void testDeleteCity() throws AlreadyExistsException, NotFoundException, InvalidArgumentFormatException {
        worldService.deleteCity(testCity.getId());
        Optional<CityDTO> fetchedCity = worldService.getCityById(testCity.getId());
        Assertions.assertTrue(fetchedCity.isEmpty());
    }

    @Test
    @Transactional
    @DisplayName("Testing if Country created has been deleted")
    public void testDeleteCountry() throws AlreadyExistsException, NotFoundException, InvalidArgumentFormatException {
        worldService.deleteCountry(testCountry.getName());
        Assertions.assertTrue(worldService.getCountryById(testCountry.getCode()).isEmpty());
    }


}


