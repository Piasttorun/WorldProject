package com.sparta.jarjarbinks.worldproject.model.services;

import com.sparta.jarjarbinks.worldproject.model.entities.CityDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountryDTO;
import com.sparta.jarjarbinks.worldproject.model.repositories.CityRepository;
import com.sparta.jarjarbinks.worldproject.model.repositories.CountryRepository;
import com.sparta.jarjarbinks.worldproject.model.repositories.CountrylanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraReactiveRepositoriesAutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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


    //What percentage of a given countries population lives in its largest city Uyi


    //Which country has the most cities? How many cites does it have? Mateusz

    public CountryDTO getCountryMostCities() {
        int freq = 0;
        String res = "";

        ArrayList<String> temp = new ArrayList<>();
        for (CityDTO city : cityRepository.findAll()) {
            temp.add(String.valueOf(city.getCountryCode()));
        }
        for (int i = 0; i < temp.size(); i++) {
            int count = 0;
            for (int j = i + 1; j < temp.size(); j++) {
                if (temp.get(j) == temp.get(i)) {
                    count++;
                }
            }
            // updating our max freq of occurred string in the
            // array of strings
            if (count >= freq) {
                res = temp.get(i);
                freq = count;
            }
        }
        return countryRepository.findByCode(res);
    }


    //which 5 districts have the smallest population? Bianca


    //For a given country, approximately how many people speak its most popular official language? Affiq


}
