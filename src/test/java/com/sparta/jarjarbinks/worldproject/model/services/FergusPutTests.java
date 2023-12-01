package com.sparta.jarjarbinks.worldproject.model.services;

import com.sparta.jarjarbinks.worldproject.exceptions.InvalidArgumentFormatException;
import com.sparta.jarjarbinks.worldproject.exceptions.NotFoundException;
import com.sparta.jarjarbinks.worldproject.model.entities.CityDTO;
import com.sparta.jarjarbinks.worldproject.model.repositories.CityRepository;
import com.sparta.jarjarbinks.worldproject.model.repositories.CountryRepository;
import com.sparta.jarjarbinks.worldproject.model.repositories.CountrylanguageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

public class FergusPutTests {

    @Mock
    private CityRepository cityRepositoryTest;
    @Mock
    private CountryRepository countryRepositoryTest;
    @Mock
    private CountrylanguageRepository countryLanguageRepositoryTest;

    private WorldService worldService = new WorldService(cityRepositoryTest, countryRepositoryTest, countryLanguageRepositoryTest);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPutCityWithValidId() throws NotFoundException, InvalidArgumentFormatException {
        // Mock data
        CityDTO existingCityDTO = new CityDTO();
        existingCityDTO.setId(1);
        existingCityDTO.setDistrict("District To Update");
        existingCityDTO.setName("City To Update");
        existingCityDTO.setPopulation(10000);

        CityDTO newCityDTO = new CityDTO();
        newCityDTO.setId(2);
        newCityDTO.setDistrict("Updated District");
        newCityDTO.setName("Updated City");
        newCityDTO.setPopulation(20000);

        cityRepositoryTest.save(existingCityDTO);

        // Mock repository behavior
        when(cityRepositoryTest.findById(existingCityDTO.getId())).thenReturn(Optional.of(existingCityDTO));

        // Call the method
        Optional<CityDTO> result = worldService.putCity(newCityDTO, existingCityDTO.getId());

        // Verify repository method calls
        verify(cityRepositoryTest).findById(existingCityDTO.getId());
        verify(cityRepositoryTest).save(existingCityDTO);

        // Assertions
        assertTrue(result.isPresent());
        assertEquals(newCityDTO, result.get());
    }
}

//import com.sparta.jarjarbinks.worldproject.exceptions.InvalidArgumentFormatException;
//import com.sparta.jarjarbinks.worldproject.exceptions.NotFoundException;
//import com.sparta.jarjarbinks.worldproject.model.entities.CityDTO;
//import com.sparta.jarjarbinks.worldproject.model.repositories.CityRepository;
//import com.sparta.jarjarbinks.worldproject.model.repositories.CountryRepository;
//import com.sparta.jarjarbinks.worldproject.model.repositories.CountrylanguageRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class FergusPutTests {
//
//    private WorldService worldServiceTest;
//    private CityRepository cityRepositoryTest;
//    private CountryRepository countryRepositoryTest;
//    private CountrylanguageRepository countryLanguageRepositoryTest;
//
//    @Test
//    @DisplayName("Check that city is updated")
//    void putCityTest() throws NotFoundException, InvalidArgumentFormatException {
//
//        CityDTO exisitingCityDTO = new CityDTO();
//        exisitingCityDTO.setId(1);
//        exisitingCityDTO.setDistrict("District To Update");
//        exisitingCityDTO.setName("City To Update");
//        exisitingCityDTO.setPopulation(10000);
//
//        CityDTO newCityDTO = new CityDTO();
//        newCityDTO.setId(2);
//        newCityDTO.setDistrict("Updated District");
//        newCityDTO.setName("Updated City");
//        newCityDTO.setPopulation(20000);
//
//        cityRepositoryTest.save(exisitingCityDTO);
//
//        Optional<CityDTO> result = worldServiceTest.putCity(newCityDTO, exisitingCityDTO.getId());
//
//        Assertions.assertTrue(result.isPresent());
//        Assertions.assertEquals(newCityDTO, result.get());
//
//    }
//
//    @Test
//    @DisplayName("Check that country is updated")
//    void putCountryTest() {
//    }
//
//    @Test
//    @DisplayName("Check that country language is updated")
//    void putCountryLanguageTest() {
//    }
//
//}