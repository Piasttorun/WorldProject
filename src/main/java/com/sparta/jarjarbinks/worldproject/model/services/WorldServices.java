package com.sparta.jarjarbinks.worldproject.model.services;

import com.sparta.jarjarbinks.worldproject.model.entities.CityDTO;
import com.sparta.jarjarbinks.worldproject.model.repositories.CityRepository;
import com.sparta.jarjarbinks.worldproject.model.repositories.CountryRepository;
import com.sparta.jarjarbinks.worldproject.model.repositories.CountrylanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorldServices {

    private CityRepository cityRepository;
    private CountryRepository countryRepository;
    private CountrylanguageRepository countrylanguageRepository;

    @Autowired
    public WorldServices(CityRepository cityRepository, CountryRepository countryRepository, CountrylanguageRepository countrylanguageRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
        this.countrylanguageRepository = countrylanguageRepository;
    }
}
