package com.sparta.jarjarbinks.worldproject.model.services;

import com.sparta.jarjarbinks.worldproject.model.entities.CityDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountryDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountrylanguageDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountrylanguageIdDTO;
import com.sparta.jarjarbinks.worldproject.model.repositories.CityRepository;
import com.sparta.jarjarbinks.worldproject.model.repositories.CountryRepository;
import com.sparta.jarjarbinks.worldproject.model.repositories.CountrylanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class WorldService {

    private CityRepository cityRepository;
    private CountryRepository countryRepository;
    private CountrylanguageRepository countrylanguageRepository;

    @Autowired
    public WorldService(CityRepository cityRepository, CountryRepository countryRepository, CountrylanguageRepository countrylanguageRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
        this.countrylanguageRepository = countrylanguageRepository;
    }

    public Optional<CityDTO> putCity(CityDTO newCity, Integer id) {

        Optional<CityDTO> existingCity = cityRepository.findById(id);

        Optional<CityDTO> result = Optional.empty();

        if (existingCity.isPresent()) {
            CityDTO cityToPut = existingCity.get();
            cityToPut = newCity;
            cityToPut.setId(id);
            cityRepository.save(cityToPut);
        }

        return result;

    }

    public Optional<CountryDTO> putCountry(CountryDTO newCountry, String code) {

        Optional<CountryDTO> existingCountry = countryRepository.findById(code);

        Optional<CountryDTO> result = Optional.empty();

        if (existingCountry.isPresent()) {
            CountryDTO countryToPut = existingCountry.get();
            countryToPut = newCountry;
            countryToPut.setCode(code);
            countryRepository.save(countryToPut);
        }

        return result;

    }

    public Optional<CountrylanguageDTO> patchCountryLanguage(CountrylanguageDTO newCountryLanguage, CountrylanguageIdDTO id) {

        Optional<CountrylanguageDTO> existingCountryLanguage = countrylanguageRepository.findById(id);

        Optional<CountrylanguageDTO> result = Optional.empty();

        if (existingCountryLanguage.isPresent()) {
            CountrylanguageDTO countryLanguageToPut = existingCountryLanguage.get();
            countryLanguageToPut = newCountryLanguage;
            countryLanguageToPut.setId(id);
            countrylanguageRepository.save(countryLanguageToPut);
        }

        return result;

    }



    //Which countries have no Head of State? Fergus


    //What percentage of a given countries population lives in its largest city Uyi


    //Which country has the most cities? How many cites does it have? Mateusz


    //which 5 districts have the smallest population? Bianca


    //For a given country, approximately how many people speak its most popular official language? Affiq


}
