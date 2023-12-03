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
import com.sparta.jarjarbinks.worldproject.model.services.ApiKeyService;
import com.sparta.jarjarbinks.worldproject.model.services.WorldService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
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

    private final ApiKeyService apiKeyService;

    private final Logger logger = Logger.getLogger(WorldController.class.getName());
    private FileHandler fileHandler;

    @Autowired
    public WorldController(WorldService worldService,
                           CountryRepository countryRepository,
                           CountrylanguageRepository countrylanguageRepository,
                           ApiKeyService apiKeyService,
                           HttpServletRequest request) {
        this.worldService = worldService;
        this.apiKeyService = apiKeyService;
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

    @Operation(summary = "Delete a city specified by integer ID.")
    @Tag(name = "City API")
    @DeleteMapping("/city/{id}")
    public void deleteCity(@PathVariable Integer id) throws NotFoundException, InvalidArgumentFormatException {
        worldService.deleteCity(id);
    }

    @Operation(summary = "Create a new city using a supplied JSON body.")
    @Tag(name = "City API")
    @PostMapping("/city")
    public void createCity(@RequestBody CityDTO newCity) throws AlreadyExistsException {
        worldService.createCity(newCity);
    }

    @Operation(summary = "Get a city by its specified integer ID.")
    @Tag(name = "City API")
    @GetMapping("/city/{id}")
    public Optional<CityDTO> getCityById(@PathVariable Integer id) {
        CityDTO cities = worldService.getCityById(id).get();
        return Optional.of(cities);
    }

    @Operation(summary = "Get all cities.")
    @Tag(name = "City API")
    @GetMapping("/city")
    public List<CityDTO> getCityById() {
        logger.log(Level.INFO, request.getRemoteAddr());
        return worldService.getCity();
    }

    @Operation(summary = "Update a city record using a supplied JSON body and specified integer ID")
    @Tag(name = "City API")
    @PutMapping("/city/{id}")
    public Optional<CityDTO> patchCity(@RequestBody CityDTO newCity, @PathVariable Integer id) throws NotFoundException, InvalidArgumentFormatException {
        logger.log(Level.INFO, request.getRemoteAddr());
        return worldService.putCity(newCity, id);
    }

    @Operation(summary = "Delete a country by its specified country code.")
    @Tag(name = "Country API")
    @DeleteMapping("/country/{name}")
    public void deleteCountry(@PathVariable String name) throws NotFoundException, InvalidArgumentFormatException {
        logger.log(Level.INFO, request.getRemoteAddr());
        worldService.deleteCountry(name);
    }

    @Operation(summary = "Create a new country using a supplied JSON body.")
    @Tag(name = "Country API")
    @PostMapping("/country")
    public void createCountry(@RequestBody CountryDTO newCity) throws AlreadyExistsException {
        logger.log(Level.INFO, request.getRemoteAddr());
        worldService.createCountry(newCity);
    }

    @Operation(summary = "Create a new country using a supplied JSON body and API key.")
    @Tag(name = "Country API")
    @PostMapping("/country/{apikey}")
    public void createCountryWithApiKey(@RequestBody CountryDTO newCity,
                                        @PathVariable String apikey) throws Exception {
        logger.log(Level.INFO, request.getRemoteAddr());
        if (apiKeyService.checkApiKey(apikey))
            worldService.createCountry(newCity);
        else
            throw new Exception("Invalid API key");
    }

    @Operation(summary = "Get a country by its specified country code.")
    @Tag(name = "Country API")
    @GetMapping("/country/{code}")
    public Optional<CountryDTO> getCountryById(@PathVariable String code) {
        logger.log(Level.INFO, request.getRemoteAddr());
        Optional<CountryDTO> countries = worldService.getCountryById(code);
        return countries;
    }

    @Operation(summary = "Get all countries.")
    @Tag(name = "Country API")
    @GetMapping("/country")
    public List<CountryDTO> getCountry() {
       logger.log(Level.INFO, request.getRemoteAddr());
       return worldService.getCountry();
    }

    @Operation(summary = "Update a country by a supplied JSON body and its country code.")
    @Tag(name = "Country API")
    @PutMapping("/country/{id}")
    public Optional<CountryDTO> patchCountry(@RequestBody CountryDTO newCity, @PathVariable Integer id) throws NotFoundException, InvalidArgumentFormatException {
        logger.log(Level.INFO, request.getRemoteAddr());
        return worldService.putCountry(newCity, String.valueOf(id));
    }

    @Operation(summary = "Delete country language by supplying CountrylanguageIdDTO languageID")
    @Tag(name = "Country Language API")
    @DeleteMapping("/country_language/{language}/{countryCode}")
    public void deleteCountryLanguage(@PathVariable String language,@PathVariable String countryCode) throws NotFoundException, InvalidArgumentFormatException {
        logger.log(Level.INFO, request.getRemoteAddr());
        worldService.deleteCountryLanguage(language,countryCode);
    }

    @Operation(summary = "Create country language by supplying countrylanguageDTO")
    @Tag(name = "Country Language API")
    @PostMapping("/country_language")
    public void createCountryLanguage(@RequestBody CountrylanguageDTO newCountrylanguage) throws AlreadyExistsException {
        logger.log(Level.INFO, request.getRemoteAddr());
        worldService.createCountryLanguage(newCountrylanguage);
    }

    @Operation(summary = "Get country language")
    @Tag(name = "Country Language API")
    @GetMapping("/country_language")
    public List<CountrylanguageDTO> getCountryLanguage() {
        logger.log(Level.INFO, request.getRemoteAddr());
        return worldService.getCountrylanguage();
    }


    @Operation(summary = "Update country language by supplying two CountrylanguageDTOs for one to be updated and one to be updated to")
    @Tag(name = "Country Language API")
    @PutMapping("/country_language/{id}")
    public Optional<CountrylanguageDTO> patchCountryLanguage(@RequestBody CountrylanguageDTO newLanguage, @RequestBody CountrylanguageDTO oldLanguage) throws NotFoundException, InvalidArgumentFormatException {
        logger.log(Level.INFO, request.getRemoteAddr());
        return worldService.putCountryLanguage(newLanguage, oldLanguage);
    }

    // Special case methods

    // fergus
    @Operation(summary = "Get a list of all countries with no head of state")
    @Tag(name = "Special API")
    @GetMapping("/countriesNoHeadOfState")
    public List<CountryDTO> countriesNoHeadOfState() {
        logger.log(Level.INFO, request.getRemoteAddr());
        return worldService.getCountriesNoHeadOfState();
    }

    @Operation(summary = "Returns what percentage of a given countries population lives in its largest city")
    @Tag(name = "Special API")
    @GetMapping("/percentagePopulationLargestCity")
    public double percentagePopulationLargestCity(@RequestBody CountryDTO newCountry) {
        logger.log(Level.INFO, request.getRemoteAddr());
        return worldService.getPercentagePopulationLargestCity(newCountry);
    }

    // mati
    @Operation(summary = "Returns the country that has the most cities")
    @Tag(name = "Special API")
    @GetMapping("/countryMostCities")
    public String countryMostCities() {
        logger.log(Level.INFO, request.getRemoteAddr());
        CountryDTO city = worldService.getCountryMostCities();
        return "City name: " + city.getName() + " and amount: " + city.getCities().size() + ".";
    }

    // bianca
    @Operation(summary = "Returns a list of the five districts that have the smallest population")
    @Tag(name = "Special API")
    @GetMapping("/citiesSmallestPopulation")
    public List<String> districtSmallestPopulation() {
        logger.log(Level.INFO, request.getRemoteAddr());
        return worldService.getSmallestPopulationDistricts();
    }

    // affiq
    @Operation(summary = "Get the population of people who speak the country's most popular language by country code")
    @Tag(name = "Special API")
    @GetMapping("/popular-language/{code}")
    public Integer SpeakMostPopularLanguage(@PathVariable String code) throws NotFoundException {
        logger.log(Level.INFO, request.getRemoteAddr());
        return worldService.getNumberOfPopularLanguageSpeakers(code);
    }

}