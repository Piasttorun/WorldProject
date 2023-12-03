package com.sparta.jarjarbinks.worldproject.model.services;

import com.sparta.jarjarbinks.worldproject.controller.WorldController;
import com.sparta.jarjarbinks.worldproject.exceptions.*;
import com.sparta.jarjarbinks.worldproject.model.entities.CityDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountryDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountrylanguageDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountrylanguageIdDTO;
import com.sparta.jarjarbinks.worldproject.model.repositories.CityRepository;
import com.sparta.jarjarbinks.worldproject.model.repositories.CountryRepository;
import com.sparta.jarjarbinks.worldproject.model.repositories.CountrylanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class WorldService {

    private CityRepository cityRepository;
    private CountryRepository countryRepository;
    private CountrylanguageRepository countrylanguageRepository;

    private final Logger logger = Logger.getLogger(WorldService.class.getName());
    private FileHandler fileHandler;


    @Autowired
    public WorldService(CityRepository cityRepository, CountryRepository countryRepository, CountrylanguageRepository countrylanguageRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
        this.countrylanguageRepository = countrylanguageRepository;
        // Log init
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.ALL);
        try {
            fileHandler = new FileHandler("src/main/resources/worldServiceLog.log", true);
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new WorldServiceLogFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Failed to connect to worldServiceLog file.");
        }
    }

    //Create methods...
    public CityDTO createCity(CityDTO cityDTO) throws AlreadyExistsException {
        if (cityRepository.findById(cityDTO.getId()).isPresent()) {
            logger.log(Level.INFO, "AlreadyExistsException: "+cityDTO.getId().toString());
            throw new AlreadyExistsException(cityDTO.getId().toString());
        } else {
            cityRepository.save(cityDTO);
            logger.log(Level.INFO, "New CityDTO created successfully.");
            return cityDTO;
        }
    }

    public CountryDTO createCountry(CountryDTO countryDTO) throws AlreadyExistsException {

        if (countryRepository.findById(countryDTO.getCode()).isPresent()) {
            logger.log(Level.INFO, "AlreadyExistsException: "+countryDTO.getCode());
            throw new AlreadyExistsException(countryDTO.getCode());
        } else {
            countryRepository.save(countryDTO);
            logger.log(Level.INFO, "New CountryDTO created successfully.");
            return countryDTO;
        }
    }

    public CountrylanguageDTO createCountryLanguage(CountrylanguageDTO countrylanguageDTO) throws AlreadyExistsException {
        if (countrylanguageRepository.findById(countrylanguageDTO.getId()).isPresent()) {
            logger.log(Level.INFO, "AlreadyExistsException: "+countrylanguageDTO.getId());
            throw new AlreadyExistsException(countrylanguageDTO.getId().toString());
        } else {
            logger.log(Level.INFO, "New CountryLanguageDTO created successfully.");
            countrylanguageRepository.save(countrylanguageDTO);
            return countrylanguageDTO;
        }
    }

    public Optional<CityDTO> getCityById(Integer id) {
        try {
            logger.log(Level.INFO, "Finding City by ID");
            return cityRepository.findById(id);
        } catch (Exception e) {
            logger.log(Level.INFO, "Exception: No such ID " + e.getMessage());
            return Optional.empty();
        }
    }

    public List<CityDTO> getCity() {
        logger.log(Level.INFO, "Finding all cities.");
        return cityRepository.findAll();
    }

    public List<CountryDTO> getCountry() {
        logger.log(Level.INFO, "Finding all countries.");
        return countryRepository.findAll();
    }

    public List<CountrylanguageDTO> getCountrylanguage() {
        logger.log(Level.INFO, "Finding all country languages.");
        return countrylanguageRepository.findAll();
    }

    public Optional<CountryDTO> getCountryById(String code) {
        try {
            logger.log(Level.INFO, "Finding Country by ID");
            return countryRepository.findById(code);
        } catch (Exception e) {
            logger.log(Level.INFO, "Exception: No such ID " + e.getMessage());
            return Optional.empty();
        }
    }

    public CountrylanguageDTO getCountryLanguageById(CountrylanguageIdDTO language) {
        try {
            logger.log(Level.INFO, "Finding Country Language by ID");
            return countrylanguageRepository.findCountrylanguageDTOById(language);
        } catch (Exception e) {
            logger.log(Level.INFO, "Exception: No such ID " + e.getMessage());
            // null different return type
            return null;
        }
    }

    public Optional<CityDTO> putCity(CityDTO newCity, Integer id) throws InvalidArgumentFormatException, NotFoundException {

        if (id == null || id < 1 || id > 500) {
            logger.log(Level.INFO, "InvalidArgumentException: Id cannot be null and must be within the range of 1-500");
            throw new InvalidArgumentFormatException("Invalid input format: id cannot be null and must be within the range of 1-500");
        }

        Optional<CityDTO> existingCity = cityRepository.findById(id);

        if (existingCity.isPresent()) {
            CityDTO cityToPut = newCity;
            cityToPut.setId(existingCity.get().getId());
            cityRepository.save(cityToPut);
            logger.log(Level.INFO, "CityDTO has been changed ");
            return Optional.of(cityToPut);
        } else {
            logger.log(Level.INFO, "NotFoundException: City not found ");
            throw new NotFoundException("Error: City not found");
        }
    }


    public Optional<CountryDTO> putCountry(CountryDTO newCountry, String code) throws InvalidArgumentFormatException, NotFoundException {

        if (code == null || code.length() != 3) {
            logger.log(Level.INFO, "InvalidArgumentException: id cannot be null and must be 3 characters long");
            throw new InvalidArgumentFormatException("Invalid input format: id cannot be null and must be 3 characters long");
        }

        Optional<CountryDTO> existingCountry = countryRepository.findById(code);

        if (existingCountry.isPresent()) {
            CountryDTO countryToPut = newCountry;
            countryToPut.setCode(existingCountry.get().getCode());
            countryRepository.save(countryToPut);
            logger.log(Level.INFO, "CountryDTO has been changed ");
            return Optional.of(countryToPut);
        } else {
            logger.log(Level.INFO, "NotFoundException: Country not found ");
            throw new NotFoundException("Error: Country not found");
        }
    }

    public Optional<CountrylanguageDTO> putCountryLanguage(CountrylanguageDTO newCountryLanguage, CountrylanguageDTO oldCountryLanguage) throws InvalidArgumentFormatException, NotFoundException {

        if (oldCountryLanguage == null) {
            logger.log(Level.INFO, "InvalidArgumentException: id cannot be null and must be 3 characters long");
            throw new InvalidArgumentFormatException("Invalid input format: id cannot be null and must be 3 characters long");
        }

        CountrylanguageDTO existingCountryLanguage = countrylanguageRepository.findCountrylanguageDTOById(oldCountryLanguage.getId());

        if (existingCountryLanguage.equals(newCountryLanguage)) {
            CountrylanguageDTO countryLanguageToPut = newCountryLanguage;
            countryLanguageToPut.setId(newCountryLanguage.getId());
            countrylanguageRepository.save(countryLanguageToPut);
            logger.log(Level.INFO, "CountryLanguageDTO has been changed ");
            return Optional.of(countryLanguageToPut);
        } else {
            logger.log(Level.INFO, "NotFoundException: Country Language not found ");
            throw new NotFoundException("Error: Country Language not found");
        }
    }

    //Which countries have no Head of State? Fergus
    public List<CountryDTO> getCountriesNoHeadOfState() {

        List<CountryDTO> countriesWithNoHeadOfState = new ArrayList<>();
        List<CountryDTO> countriesList = countryRepository.findAll();

        for (CountryDTO country : countriesList) {
            if (country.getHeadOfState() == null || country.getHeadOfState().equals("")) {
                countriesWithNoHeadOfState.add(country);
            }
        }
        logger.log(Level.INFO, "Returning countries with no head of state ");
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
        logger.log(Level.INFO, "Returning percentage population of largest city");
        return ((double) largestCity.getPopulation() / country.getPopulation()) * 100;
    }

    public double getPercentagePopulationLargestCity(String countryCode) throws NotFoundException {
        Optional<CountryDTO> countryList;

        if(countryRepository.findByCode(countryCode).isEmpty()){
            logger.log(Level.INFO, "NotFoundException: Country code does not exist");
            throw new NotFoundException("Country code does not exist");
        }else {
            countryList = countryRepository.findByCode(countryCode);
        }
        List<CityDTO> cities = cityRepository.findAllByCountryCode(countryList.get());

        CityDTO largestCity = cities.get(0);

        for(CityDTO city: cities){
            if(city.getPopulation() > largestCity.getPopulation()){
                largestCity = city;
            }
        }
        logger.log(Level.INFO, "Returning percentage population of largest city");
        return ((double) largestCity.getPopulation() / countryList.get().getPopulation()) * 100;
    }


    //Which country has the most cities? How many cites does it have? Mateusz
    public CountryDTO getCountryMostCities() {

        int currentLargestAmount = 0;
        CountryDTO currentLargestCountry = null;
        for (CountryDTO country : countryRepository.findAll()) {
            if (country.getCities().size() > currentLargestAmount) {
                currentLargestAmount = country.getCities().size();
                currentLargestCountry = country;
            }
        }
        logger.log(Level.INFO, "Returning countries with the most cities");
        return currentLargestCountry;
    }


    //which 5 districts have the smallest population? Bianca
    public List<String> getSmallestPopulationDistricts() {
        try {
            List<CityDTO> cities = cityRepository.findAll();
            logger.log(Level.INFO, "Returning smallest population districts...");
            return cities.stream()
                    .sorted(Comparator.comparing(CityDTO::getPopulation))
                    .limit(5)
                    .map(CityDTO::getDistrict)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.log(Level.INFO, "Exception: Failed to get smallest population districts: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    //For a given country, approximately how many people speak its most popular official language?Affiq
    public Integer getNumberOfPopularLanguageSpeakers(String countryCode) throws NotFoundException {

        if (countryRepository.findByCode(countryCode).isEmpty()) {
            logger.log(Level.INFO, "NotFoundException: Country Code not found");
            throw new NotFoundException("Country code not found");
        }
        CountryDTO countryDTO = countryRepository.findByCode(countryCode).get();
        List<CountrylanguageDTO> spokenLanguages
                = countrylanguageRepository.findAllByCountryCodeOrderByPercentageDesc(countryDTO);

        BigDecimal percentage = spokenLanguages.get(0).getPercentage().divide(new BigDecimal(100));

        logger.log(Level.INFO, "Returning population of most popular language speakers");
        return  percentage.multiply(BigDecimal.valueOf(countryDTO.getPopulation())).intValue();
    }

    public void deleteCity(Integer id) throws NotFoundException, InvalidArgumentFormatException {
        if(id == null){
            logger.log(Level.INFO, "InvalidArgumentFormatException: Invalid input into deleteCity method");
            throw new InvalidArgumentFormatException("Invalid input into deleteCity method");
        }
        if(cityRepository.findById(id).isEmpty()){
            logger.log(Level.INFO, "NotFoundException: City could not be found");
            throw new NotFoundException("This city could not be found.");
        }else {
            logger.log(Level.INFO, "Deleting city");
            cityRepository.deleteById(id);
        }
    }
    public void deleteCountry(Integer countryId) throws InvalidArgumentFormatException, NotFoundException {

        if(countryId == null){
            logger.log(Level.INFO, "InvalidArgumentFormatException: Invalid input into deleteCountry method");
            throw new InvalidArgumentFormatException("Invalid input into deleteCountry method");
        }

        if(countryRepository.findById(countryId.toString()).isEmpty()){
            logger.log(Level.INFO, "NotFoundException: Country could not be found");
            throw new NotFoundException("This Country could not be found.");
        } else {
            Optional<CountryDTO> thisCountry = countryRepository.findById(countryId.toString());
            List<CityDTO> thisCity = cityRepository.findAllByCountryCode(thisCountry);
            countryRepository.deleteCountryDTOByCode(thisCountry.get().getCode());
            logger.log(Level.INFO, "Deleting country");
            cityRepository.deleteAllByCountryCode(thisCountry);
        }
    }

    public void deleteCountry(String country) throws InvalidArgumentFormatException, NotFoundException {
        if (country.isEmpty()){
            logger.log(Level.INFO, "InvalidArgumentFormatException: Invalid input into deleteCountry method");
            throw new InvalidArgumentFormatException("Invalid input into deleteCountry method");
        }

        if(countryRepository.findAllByName(country).isEmpty()){
            logger.log(Level.INFO, "NotFoundException: Country could not be found");
            throw new NotFoundException("This Country could not be found.");
        } else {
            logger.log(Level.INFO, "Deleting country");
            countryRepository.deleteAllByName(country);
        }
    }

    public void deleteCountryLanguage(CountrylanguageIdDTO languageID) throws InvalidArgumentFormatException, NotFoundException {
        if(languageID == null){
            logger.log(Level.INFO, "NotFoundException: Invalid language entered");
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
            logger.log(Level.INFO, "NotFoundException: Language ID could not be found.");
            throw new NotFoundException("The Language " + languageID + " could not be found.");
        }
        logger.log(Level.INFO, "Country Language deleted");
    }
    public void deleteCountryLanguage(String language,String countryCode) throws InvalidArgumentFormatException, NotFoundException {
        if(language == null || countryCode == null){
            logger.log(Level.INFO, "NotFoundException: Invalid language or country code entered");
            throw new InvalidArgumentFormatException("Invalid language was entered make sure the language is Capitalized");
        }
        int count = 0;

        List<CountrylanguageDTO> languages = countrylanguageRepository.findAll();
        for(CountrylanguageDTO country: languages){
            if(country.getId().getLanguage().equals(language) && country.getId().getCountryCode().equals(countryCode)){
                countrylanguageRepository.delete(country);
                count++;
            }
        }
        if (count == 0){
            logger.log(Level.INFO, "NotFoundException: Language ID could not be found.");
            throw new NotFoundException("The Language " + language + " could not be found.");
        }
        logger.log(Level.INFO, "Country Language deleted");
    }

    public List<CountrylanguageDTO> getAllCountryLanguages() {
        try {
            logger.log(Level.INFO, "Attempting to find all country languages");
            return countrylanguageRepository.findAll();
        } catch (Exception e) {
            logger.log(Level.INFO, "NotFoundException: Failed to get all country languages.");
            return Collections.emptyList();
        }
    }
}