package com.sparta.jarjarbinks.worldproject.model.services;

import com.sparta.jarjarbinks.worldproject.model.entities.CityDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountryDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountrylanguageDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountrylanguageIdDTO;
import com.sparta.jarjarbinks.worldproject.model.repositories.CityRepository;
import com.sparta.jarjarbinks.worldproject.model.repositories.CountryRepository;
import com.sparta.jarjarbinks.worldproject.model.repositories.CountrylanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraReactiveRepositoriesAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.List;

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

    //Create methods...
    public CityDTO createCity(CityDTO cityDTO) {
        cityRepository.save(cityDTO);
        return cityDTO;
    }

    public CountryDTO createCountry(CountryDTO countryDTO) {
        countryRepository.save(countryDTO);
        return countryDTO;
    }

    public CountrylanguageDTO createCountryLanguage(CountrylanguageDTO countrylanguageDTO) {
        countrylanguageRepository.save(countrylanguageDTO);
        return countrylanguageDTO;
    }

    public Optional<CityDTO> getCityById(Integer id) {
        return cityRepository.findById(id);
    }

    public Optional<CountryDTO> getCountryByCode(String code) {
        return countryRepository.findById(code);
    }

    public Optional<CountrylanguageDTO> getCountrylanguageById(CountrylanguageIdDTO id) {
        return countrylanguageRepository.findById(id);
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
    public List<CountryDTO> getCountriesNoHeadOfState() {

        List<CountryDTO> countriesWithNoHeadOfState = new ArrayList<>();
        List<CountryDTO> countriesList = countryRepository.findAll();

        for (CountryDTO country : countriesList) {
            if (country.getHeadOfState().isEmpty()) {
                countriesWithNoHeadOfState.add(country);
            }
        }

        return countriesWithNoHeadOfState;
    }



    //What percentage of a given countries population lives in its largest city - uyi
    public int getPercentagePopulationLargestCity(CountryDTO country){
        List<CityDTO> cities = cityRepository.findAllByCountryCode(country);
        CityDTO largestCity = cities.get(0);

        for(CityDTO city: cities){
            if(city.getPopulation() > largestCity.getPopulation()){
                largestCity = city;
            }
        }
        return (largestCity.getPopulation() / country.getPopulation()) * 100;
    }

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
    public List<String> getSmallestPopulationDistricts() {
        List<CityDTO> cities = cityRepository.findAll();

        return cities.stream()
                .sorted(Comparator.comparing(CityDTO::getPopulation))
                .limit(5)
                .map(CityDTO::getDistrict)
                .collect(Collectors.toList());
    }

    //For a given country, approximately how many people speak its most popular official language?Affiq

    //For a given country, approximately how many people speak its most popular official language?
    public Integer getNumberOfPopularLanguageSpeakers(CountryDTO countryDTO) {
        List<CountrylanguageDTO> spokenLanguages
                = countrylanguageRepository.findAllByCountryCodeOrderByPercentageDesc(countryDTO);

        BigDecimal percentage = spokenLanguages.get(0).getPercentage().divide(new BigDecimal(100));

        return  percentage.multiply(BigDecimal.valueOf(countryDTO.getPopulation())).intValue();
    }

    public void deleteCity(Integer id){
        cityRepository.deleteById(id);
    }
    public void deleteCountry(Integer id){
        Optional<CountryDTO> thisCountry = countryRepository.findById(id.toString());
        List<CityDTO> thisCity = cityRepository.findAllByCountryCode(thisCountry);

        if(thisCountry.isPresent()){
            countryRepository.delete(thisCountry);
            cityRepository.deleteAllByCountryCode(thisCountry);
        }
    }

    public void deleteCountryLanguage(CountrylanguageIdDTO countrylanguageIdDTO){
        countrylanguageRepository.deleteById(countrylanguageIdDTO);
    }
}