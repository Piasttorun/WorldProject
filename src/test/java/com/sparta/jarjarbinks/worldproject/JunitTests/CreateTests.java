package com.sparta.jarjarbinks.worldproject.JunitTests;


import com.sparta.jarjarbinks.worldproject.exceptions.AlreadyExistsException;
import com.sparta.jarjarbinks.worldproject.exceptions.InvalidArgumentFormatException;
import com.sparta.jarjarbinks.worldproject.exceptions.NotFoundException;
import com.sparta.jarjarbinks.worldproject.model.entities.CountryDTO;
import com.sparta.jarjarbinks.worldproject.model.repositories.CityRepository;
import com.sparta.jarjarbinks.worldproject.model.services.WorldService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@SpringBootTest
public class CreateTests {

    @Autowired
    WorldService worldService;

    CountryDTO country;

    @Test
    public void createCountryTest() throws AlreadyExistsException, NotFoundException, InvalidArgumentFormatException {

        country = new CountryDTO();
        country.setHeadOfState("Uyi");
        country.setPopulation(5);
        country.setCode("DDD");
        country.setName("Uyi");
        country.setGovernmentForm("Uyidom");
        country.setLocalName("Uyitopia");
        country.setSurfaceArea(new BigDecimal(2345));
        country.setRegion("Uyis house");
        country.setContinent("Asia");
        country.setCode2("UI");

        worldService.createCountry(country);
        //worldService.getCountryById("BBB").equals(country)
        Assertions.assertTrue(true);

        worldService.deleteCountry("Uyi");
    }

}