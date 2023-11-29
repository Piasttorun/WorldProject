package com.sparta.jarjarbinks.worldproject.model.services;

import com.sparta.jarjarbinks.worldproject.model.entities.CityDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountryDTO;
import com.sparta.jarjarbinks.worldproject.model.repositories.CityRepository;
import com.sparta.jarjarbinks.worldproject.model.repositories.CountryRepository;
import com.sparta.jarjarbinks.worldproject.model.repositories.CountrylanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    //Which countries have no Head of State? Fergus



    //What percentage of a given countries population lives in its largest city - uyi
    public int getPercentagePopulationLargestCity(CountryDTO country){
        int countryCode = Integer.parseInt(country.getCode());
        List<CityDTO> cities = cityRepository.findCityDTOByCountryCode(countryCode);
        CityDTO largestCity = cities.get(0);

        for(CityDTO city: cities){
            if(city.getPopulation() > largestCity.getPopulation()){
                largestCity = city;
            }
        }
        return (largestCity.getPopulation() / country.getPopulation()) * 100;
    }

    //Which country has the most cities? How many cites does it have? Mateusz


    //which 5 districts have the smallest population? Bianca
  
    //For a given country, approximately how many people speak its most popular official language?Affiq

    public void deleteCity(Integer id){
        cityRepository.deleteById(id);
    }
    public void deleteCountry(Integer id){
        countryRepository.deleteById(id.toString());
    }

    public void deleteCountryLanguage(Integer id){
        countrylanguageRepository.deleteById(id);
    }
}
