package com.sparta.jarjarbinks.worldproject;

import com.sparta.jarjarbinks.worldproject.model.entities.CityDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountryDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountrylanguageDTO;
import com.sparta.jarjarbinks.worldproject.model.repositories.CityRepository;
import com.sparta.jarjarbinks.worldproject.model.repositories.CountryRepository;
import com.sparta.jarjarbinks.worldproject.model.services.WorldService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DeleteMethodsTest {

    private static List<CityDTO> cities;
    private static List<CountryDTO> countries;
    private static List<CountrylanguageDTO> languages;

    private static WorldService worldService;


    @BeforeAll
    void setup(){
        init();
    }

    void init(){
        CityDTO hell = new CityDTO();
        hell.setName("Hell");
        cities.add(hell);

        CountryDTO heaven = new CountryDTO();
        heaven.setName("Heaven");
        heaven.setCode("DDD");
        countries.add(heaven);

        CountrylanguageDTO space = new CountrylanguageDTO();
        space.setCountryCode(heaven);
        languages.add(space);
    }


    @Test
    public void testCityDeletion(){

    }

    @Test
    public void testCountryDeletion(){

    }

    @Test
    public void testLanguageDeletion(){

    }
}
