package com.sparta.jarjarbinks.worldproject.controller;

import com.sparta.jarjarbinks.worldproject.exceptions.AlreadyExistsException;
import com.sparta.jarjarbinks.worldproject.exceptions.ControllerLogFormatter;
import com.sparta.jarjarbinks.worldproject.exceptions.InvalidArgumentFormatException;
import com.sparta.jarjarbinks.worldproject.exceptions.NotFoundException;
import com.sparta.jarjarbinks.worldproject.model.entities.CityDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountryDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountrylanguageDTO;
import com.sparta.jarjarbinks.worldproject.model.entities.CountrylanguageIdDTO;
import com.sparta.jarjarbinks.worldproject.model.repositories.CountryRepository;
import com.sparta.jarjarbinks.worldproject.model.repositories.CountrylanguageRepository;
import com.sparta.jarjarbinks.worldproject.model.services.WorldService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class WorldController {

    private final HttpServletRequest request;
    private final WorldService worldService;

    private final Logger logger = Logger.getLogger(WorldController.class.getName());
    private FileHandler fileHandler;

    @Autowired
    public WorldController(WorldService worldService,
                           CountryRepository countryRepository,
                           CountrylanguageRepository countrylanguageRepository,
                           HttpServletRequest request) {
        this.worldService = worldService;
        this.request = request;

        // Log init
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.ALL);
        try {
            fileHandler = new FileHandler("src/main/resources/controllerLog.log", true);
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new ControllerLogFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Failed to connect to controllerLog file.");
        }

    }

    @DeleteMapping("/city/{id}")
    public void deleteCity(@PathVariable Integer id) throws NotFoundException, InvalidArgumentFormatException {
        worldService.deleteCity(id);
    }

    @PostMapping("/city")
    public void createCity(@RequestBody CityDTO newCity) throws AlreadyExistsException {
        worldService.createCity(newCity);
    }

    @GetMapping("/city/{id}")
    public Optional<CityDTO> getCityById(@PathVariable Integer id) {
        CityDTO cities = worldService.getCityById(id).get();
        return Optional.of(cities);
    }

    @GetMapping("/city")
    public List<CityDTO> getCityById() {
        logger.log(Level.INFO, request.getRemoteAddr());
        return worldService.getCity();
    }

    @PutMapping("/city/{id}")
    public Optional<CityDTO> patchCity(@RequestBody CityDTO newCity, @PathVariable Integer id) throws NotFoundException, InvalidArgumentFormatException {
        logger.log(Level.INFO, request.getRemoteAddr());
        return worldService.putCity(newCity, id);
    }

    @DeleteMapping("/country/{id}")
    public void deleteCountry(@PathVariable Integer id) throws NotFoundException, InvalidArgumentFormatException {
        logger.log(Level.INFO, request.getRemoteAddr());
        worldService.deleteCountry(id);
    }

    @PostMapping("/country")
    public void createCountry(@RequestBody CountryDTO newCity) {
        logger.log(Level.INFO, request.getRemoteAddr());
        createCountry(newCity);
    }

    @GetMapping("/country/{code}")
    public Optional<CountryDTO> getCountryById(@PathVariable String code) {
        logger.log(Level.INFO, request.getRemoteAddr());
        Optional<CountryDTO> countries = worldService.getCountryById(code);
        return countries;
    }

    @GetMapping("/country")
    public List<CountryDTO> getCountry() {
       logger.log(Level.INFO, request.getRemoteAddr());
       return worldService.getCountry();
    }

    @PutMapping("/country/{id}")
    public Optional<CountryDTO> patchCountry(@RequestBody CountryDTO newCity, @PathVariable Integer id) throws NotFoundException, InvalidArgumentFormatException {
        logger.log(Level.INFO, request.getRemoteAddr());
        return worldService.putCountry(newCity, String.valueOf(id));
    }

    @DeleteMapping("/country_language")
    public void deleteCountryLanguage(@RequestBody CountrylanguageIdDTO newCountryId) throws NotFoundException, InvalidArgumentFormatException {
        logger.log(Level.INFO, request.getRemoteAddr());
        worldService.deleteCountryLanguage(newCountryId);
    }

    @PostMapping("/country_language")
    public void createCountryLanguage(@RequestBody CountrylanguageDTO newCountrylanguage) throws AlreadyExistsException {
        logger.log(Level.INFO, request.getRemoteAddr());
        worldService.createCountryLanguage(newCountrylanguage);
    }

    @GetMapping("/country_language")
    public List<CountrylanguageDTO> getCountryLanguage() {
        logger.log(Level.INFO, request.getRemoteAddr());
        return worldService.getCountrylanguage();
    }


    @PutMapping("/country_language/{id}")
    public Optional<CountrylanguageDTO> patchCountryLanguage(@RequestBody CountrylanguageDTO newLanguage, @RequestBody CountrylanguageDTO oldLanguage) throws NotFoundException, InvalidArgumentFormatException {
        logger.log(Level.INFO, request.getRemoteAddr());
        return worldService.putCountryLanguage(newLanguage, oldLanguage);
    }

    // Special case methods

    // fergus
    @GetMapping("/countriesNoHeadOfState")
    public List<CountryDTO> countriesNoHeadOfState() {
        logger.log(Level.INFO, request.getRemoteAddr());
        return worldService.getCountriesNoHeadOfState();
    }

    // uyi
    @GetMapping("/percentagePopulationLargestCity")
    public double percentagePopulationLargestCity(@RequestBody CountryDTO newCountry) {
        logger.log(Level.INFO, request.getRemoteAddr());
        return worldService.getPercentagePopulationLargestCity(newCountry);
    }

    // mati
    @GetMapping("/countryMostCities")
    public String countryMostCities() {
        logger.log(Level.INFO, request.getRemoteAddr());
        CountryDTO city = worldService.getCountryMostCities();
        return "City name: " + city.getName() + " and amount: " + city.getCities().size() + ".";
    }

    // bianca
    @GetMapping("/citiesSmallestPopulation")
    public List<String> districtSmallestPopulation() {
        logger.log(Level.INFO, request.getRemoteAddr());
        return worldService.getSmallestPopulationDistricts();
    }

    // affiq
    @GetMapping("/popular-language/{countrycode}")
    public Integer SpeakMostPopularLanguage(@PathVariable String countrycode) throws NotFoundException {
        logger.log(Level.INFO, request.getRemoteAddr());
        return worldService.getNumberOfPopularLanguageSpeakers(countrycode);
    }

}