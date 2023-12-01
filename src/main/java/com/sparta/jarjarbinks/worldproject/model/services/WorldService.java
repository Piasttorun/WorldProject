package com.sparta.jarjarbinks.worldproject.model.services;

import com.sparta.jarjarbinks.worldproject.exceptions.AlreadyExistsException;
import com.sparta.jarjarbinks.worldproject.exceptions.InvalidArgumentFormatException;
import com.sparta.jarjarbinks.worldproject.exceptions.NotFoundException;
import com.sparta.jarjarbinks.worldproject.model.entities.CityDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountryDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountrylanguageDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountrylanguageIdDTO;
import com.sparta.jarjarbinks.worldproject.model.repositories.CityRepository;
import com.sparta.jarjarbinks.worldproject.model.repositories.CountryRepository;
import com.sparta.jarjarbinks.worldproject.model.repositories.CountrylanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public CityDTO createCity(CityDTO cityDTO) throws AlreadyExistsException {
        if (cityRepository.findById(cityDTO.getId()).isPresent()) {
            throw new AlreadyExistsException(cityDTO.getId().toString());
        } else {
            cityRepository.save(cityDTO);
            return cityDTO;
        }
    }

    public CountryDTO createCountry(CountryDTO countryDTO) throws AlreadyExistsException {

        if (countryRepository.findById(countryDTO.getCode()).isPresent()) {
            throw new AlreadyExistsException(countryDTO.getCode());
        } else {
            countryRepository.save(countryDTO);
            return countryDTO;
        }
    }

    public CountrylanguageDTO createCountryLanguage(CountrylanguageDTO countrylanguageDTO) throws AlreadyExistsException {
        if (countrylanguageRepository.findById(countrylanguageDTO.getId()).isPresent()) {
            throw new AlreadyExistsException(countrylanguageDTO.getId().toString());
        } else {
            countrylanguageRepository.save(countrylanguageDTO);
            return countrylanguageDTO;
        }
    }

    public Optional<CityDTO> getCityById(Integer id) {
        try {
            return cityRepository.findById(id);
        } catch (Exception e) {
            System.err.println("Failed to get city by ID: " + e.getMessage());
            return Optional.empty();
        }
    }

    public List<CityDTO> getCity() {
        return cityRepository.findAll();
    }

    public List<CountryDTO> getCountry() {
        return countryRepository.findAll();
    }

    public List<CountrylanguageDTO> getCountrylanguage() {
        return countrylanguageRepository.findAll();
    }

    public Optional<CountryDTO> getCountryById(String code) {
        try {
            return countryRepository.findById(code);
        } catch (Exception e) {
            System.err.println("Failed to get country by ID: " + e.getMessage());
            return Optional.empty();
        }
    }

    public CountrylanguageDTO getCountryLanguageById(CountrylanguageIdDTO language) {
        try {
            return countrylanguageRepository.findCountrylanguageDTOById(language);
        } catch (Exception e) {
            System.err.println("Failed to get language by ID: " + e.getMessage());
            // null different return type
            return null;
        }
    }

    public Optional<CityDTO> putCity(CityDTO newCity, Integer id) throws InvalidArgumentFormatException, NotFoundException {

        if (id == null || id < 1 || id > 500) {
            throw new InvalidArgumentFormatException("Invalid input format: id cannot be null and must be within the range of 1-500");
        }

        Optional<CityDTO> existingCity = cityRepository.findById(id);

        if (existingCity.isPresent()) {
            CityDTO cityToPut = newCity;
            cityToPut.setId(existingCity.get().getId());
            cityRepository.save(cityToPut);
            return Optional.of(cityToPut);
        } else {
            throw new NotFoundException("Error: City not found");
        }
    }


    public Optional<CountryDTO> putCountry(CountryDTO newCountry, String code) throws InvalidArgumentFormatException, NotFoundException {

        if (code == null || code.length() != 3) {
            throw new InvalidArgumentFormatException("Invalid input format: id cannot be null and must be 3 characters long");
        }

        Optional<CountryDTO> existingCountry = countryRepository.findById(code);

        if (existingCountry.isPresent()) {
            CountryDTO countryToPut = newCountry;
            countryToPut.setCode(existingCountry.get().getCode());
            countryRepository.save(countryToPut);
            return Optional.of(countryToPut);
        } else {
            throw new NotFoundException("Error: Country not found");
        }
    }

    public Optional<CountrylanguageDTO> putCountryLanguage(CountrylanguageDTO newCountryLanguage, CountrylanguageDTO oldCountryLanguage) throws InvalidArgumentFormatException, NotFoundException {

        if (oldCountryLanguage == null) {
            throw new InvalidArgumentFormatException("Invalid input format: id cannot be null and must be 3 characters long");
        }

        CountrylanguageDTO existingCountryLanguage = countrylanguageRepository.findCountrylanguageDTOById(oldCountryLanguage.getId());

        if (existingCountryLanguage.equals(newCountryLanguage)) {
            CountrylanguageDTO countryLanguageToPut = newCountryLanguage;
            countryLanguageToPut.setId(newCountryLanguage.getId());
            countrylanguageRepository.save(countryLanguageToPut);
            return Optional.of(countryLanguageToPut);
        } else {
            throw new NotFoundException("Error: Country Language not found");
        }
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
    public double getPercentagePopulationLargestCity(CountryDTO country){
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
        return countryRepository.findByCode(res).get();
    }


    //which 5 districts have the smallest population? Bianca
    public List<String> getSmallestPopulationDistricts() {
        try {
            List<CityDTO> cities = cityRepository.findAll();

            return cities.stream()
                    .sorted(Comparator.comparing(CityDTO::getPopulation))
                    .limit(5)
                    .map(CityDTO::getDistrict)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Failed to get smallest population districts: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    //For a given country, approximately how many people speak its most popular official language?Affiq
    public Integer getNumberOfPopularLanguageSpeakers(String countryCode) throws NotFoundException {

        if (countryRepository.findByCode(countryCode).isEmpty()) {
            throw new NotFoundException("Country code not found");
        }
        CountryDTO countryDTO = countryRepository.findByCode(countryCode).get();
        List<CountrylanguageDTO> spokenLanguages
                = countrylanguageRepository.findAllByCountryCodeOrderByPercentageDesc(countryDTO);

        BigDecimal percentage = spokenLanguages.get(0).getPercentage().divide(new BigDecimal(100));

        return  percentage.multiply(BigDecimal.valueOf(countryDTO.getPopulation())).intValue();
    }

    public void deleteCity(Integer id) throws NotFoundException, InvalidArgumentFormatException {
        if(id == null){
            throw new InvalidArgumentFormatException("Invalid input into deleteCity method");
        }
        if(cityRepository.findById(id).isEmpty()){
            throw new NotFoundException("This city could not be found.");
        }else {
            cityRepository.deleteById(id);
        }
    }
    public void deleteCountry(Integer countryId) throws InvalidArgumentFormatException, NotFoundException {

        if(countryId == null){
            throw new InvalidArgumentFormatException("Invalid input into deleteCountry method");
        }

        if(countryRepository.findById(countryId.toString()).isEmpty()){
            throw new NotFoundException("This Country could not be found.");
        } else {
            Optional<CountryDTO> thisCountry = countryRepository.findById(countryId.toString());
            List<CityDTO> thisCity = cityRepository.findAllByCountryCode(thisCountry);
            countryRepository.deleteCountryDTOByCode(thisCountry.get().getCode());
            cityRepository.deleteAllByCountryCode(thisCountry);
        }
    }

    public void deleteCountry(String country) throws InvalidArgumentFormatException, NotFoundException {
        if (country.isEmpty()){
            throw new InvalidArgumentFormatException("Invalid input into deleteCountry method");
        }

        if(countryRepository.findAllByName(country).isEmpty()){
            throw new NotFoundException("This Country could not be found.");
        } else {
            countryRepository.deleteAllByName(country);
        }
    }

    public void deleteCountryLanguage(CountrylanguageIdDTO languageID) throws InvalidArgumentFormatException, NotFoundException {
        if(languageID == null){
            throw new InvalidArgumentFormatException("Invalid language was entered make sure the language is Capitalized");
        }
        int count = 0;

        List<CountrylanguageDTO> languages = countrylanguageRepository.findAll();
        for(CountrylanguageDTO country: languages){
            if(country.getId().equals(languageID)){
                countrylanguageRepository.delete(country);
                count++;
            }
        }
        if (count == 0){
            throw new NotFoundException("The Language " + languageID + " could not be found.");
        }
    }

    public List<CountrylanguageDTO> getAllCountryLanguages() {
        try {
            return countrylanguageRepository.findAll();
        } catch (Exception e) {
            System.err.println("Failed to get all country languages: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}