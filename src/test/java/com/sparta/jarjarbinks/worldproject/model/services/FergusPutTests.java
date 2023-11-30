package com.sparta.jarjarbinks.worldproject.model.services;

import com.sparta.jarjarbinks.worldproject.exceptions.InvalidArgumentFormatException;
import com.sparta.jarjarbinks.worldproject.exceptions.NotFoundException;
import com.sparta.jarjarbinks.worldproject.model.entities.CityDTO;
import com.sparta.jarjarbinks.worldproject.model.repositories.CityRepository;
import com.sparta.jarjarbinks.worldproject.model.repositories.CountryRepository;
import com.sparta.jarjarbinks.worldproject.model.repositories.CountrylanguageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FergusPutTests {

    private WorldService worldServiceTest;
    private CityRepository cityRepositoryTest;
    private CountryRepository countryRepositoryTest;
    private CountrylanguageRepository countryLanguageRepositoryTest;

    @Test
    @DisplayName("Check that city is updated")
    void putCityTest() throws NotFoundException, InvalidArgumentFormatException {

        CityDTO exisitingCityDTO = new CityDTO();
        exisitingCityDTO.setId(1);
        exisitingCityDTO.setDistrict("District To Update");
        exisitingCityDTO.setName("City To Update");
        exisitingCityDTO.setPopulation(10000);

        CityDTO newCityDTO = new CityDTO();
        newCityDTO.setId(2);
        newCityDTO.setDistrict("Updated District");
        newCityDTO.setName("Updated City");
        newCityDTO.setPopulation(20000);

        cityRepositoryTest.save(exisitingCityDTO);

        Optional<CityDTO> result = worldServiceTest.putCity(newCityDTO, exisitingCityDTO.getId());

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(newCityDTO, result.get());

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